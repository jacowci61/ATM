import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "src/ATMData.txt";
        List<CreditCard> listOfCreditCards = WorkWithData.readStringArrayIntoObjectArray(filePath);

        //region Reading ATM data
        // here ATM data gets extracted from  CreditCards list. ATM data is stored in 1st line of .txt file
        // (and stored as first object in CreditCards list too), so at first we store ATM data as CreditCard object in list,
        //  but then extract values here

        CreditCard ATMDataAsCreditCard = listOfCreditCards.getFirst(); // get first element from list
        ATM atm = new ATM(ATMDataAsCreditCard.getBankAccountID(), ATMDataAsCreditCard.getAmountOfMoney()); // get ATM values
        listOfCreditCards.remove(0); // delete first object as it's represents ATM data and not credit card data
        //endregion

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a credit card number with or without spaces/dashes " +
                "(for example '4333-5222-6999-7444','4333 5222 6999 7444' or '4333522269997444')");
        String tempRequestedCard = reader.nextLine(); // temp because it won't be used more than once
        String userRequestedCard = WorkWithData.readInputtedCreditCard(tempRequestedCard, false); // pass tempRequestedCard to method in order to handle it in case of errors during input

        Map<String, Object> creditCardObjectAndItsIndex = ATM.creditCardAuthorization(Long.parseLong(userRequestedCard), filePath);

        CreditCard retrievedCard = (CreditCard) creditCardObjectAndItsIndex.get("credit card");
        int retrievedCardIndex = (Integer) WorkWithData.findCreditCardByNumber(listOfCreditCards, userRequestedCard).get("index");

        boolean retrievedBool = (boolean) creditCardObjectAndItsIndex.get("bool");
        boolean ATMModeSelectedCorrectly = false;
        boolean startMessageDisplayed = false;
        boolean exitFromATM = false;


        while ((ATMModeSelectedCorrectly == false) && (exitFromATM == false)){

            if (retrievedBool == true){

                if (startMessageDisplayed == false){
                    System.out.println("\nEnter 1 to view balance of this card, " +
                            "enter 2 to cashout money to card," + " enter 3 to add money to card: ");
                    startMessageDisplayed = true;
                }
                int SelectOperation = reader.nextInt();

                if (SelectOperation == 1){

                    System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
                    ATMModeSelectedCorrectly = true;
                }
                else if (SelectOperation == 2){

                    // displaying balance despite not selecting operation "1" is used due to program exit after any operation is complete
                    System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
                    retrievedCard = ATM.cashoutFromCreditCard(retrievedCard, retrievedBool, atm);
                    listOfCreditCards.set(retrievedCardIndex, retrievedCard);
                    ATMModeSelectedCorrectly = true;
                }
                else if (SelectOperation == 3){

                    // displaying balance despite not selecting operation "1" is used due to program exit after any operation is complete
                    System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
                    retrievedCard = ATM.addMoneyToCreditCard(retrievedCard, retrievedBool, atm);
                    listOfCreditCards.set(retrievedCardIndex, retrievedCard);
                    ATMModeSelectedCorrectly = true;
                }
                else{

                    System.out.println("You've selected incorrect mode of ATM. Supported ones are '1','2' or '3'. Please try again: ");
                    SelectOperation = 0;
                    SelectOperation = reader.nextInt();
                }
            }
            else{

                System.out.println("Credit card is blocked, you don't have access to any ATM operations");

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = currentDateTime.format(formatOfDate);
                LocalDateTime formattedDateOfBlock = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                if ((listOfCreditCards.get(retrievedCardIndex).getDateOfCreditCardBlock()).format(formatOfDate).equals("01-01-1970 00:00:00")){
                    retrievedCard.setDate(formattedDateOfBlock);
                    listOfCreditCards.set(retrievedCardIndex, retrievedCard);
                }
                else{
                    break;
                }
                break;
            }
        }

        String[] list1 = WorkWithData.readObjectArrayIntoStringArray(listOfCreditCards,atm);
        WorkWithData.overwriteFileWithStringArray(filePath, list1);
    }
}
