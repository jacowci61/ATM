import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "src/ATMDataTEST.txt";
        String filePath2 = "src/ATMData.txt";
        List<CreditCard> list = WorkWithData.readStringArrayIntoObjectArray(filePath);

        CreditCard cr1 = list.getFirst();
        ATM atm = new ATM(cr1.getBankAccountID(), cr1.getAmountOfMoney());
        System.out.println(list.get(0).getAmountOfMoney());
        list.remove(0);
        System.out.println(list.get(0).getAmountOfMoney());

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a credit card number with or without spaces/dashes " +
                "(for example '4333-5222-6999-7444','4333 5222 6999 7444' or '4333522269997444')");
        String RequestedCardtemp = reader.nextLine();
        String UserCard = WorkWithData.readInputtedCreditCard(RequestedCardtemp, false);

        Map<String, Object> map = ATM.Authorization(Long.parseLong(UserCard), filePath);

        CreditCard retrievedCard = (CreditCard) map.get("credit card");
        int index = (Integer) WorkWithData.findElementContainingSequence(list, UserCard).get("index");
        System.out.println("Index:" +  index);
        boolean retrievedBool = (boolean) map.get("bool");
        boolean ATMModeSelectedCorrectly = false;
        boolean StartMessageDisplayed = false;
        boolean ExitfromATM = false;
        String ExitfromATMstr = "";
        Scanner reader1 = new Scanner(System.in);


        while ((ATMModeSelectedCorrectly == false) && (ExitfromATM == false)){

            if (retrievedBool == true){

                if (StartMessageDisplayed == false){
                    System.out.println("\nEnter 1 to view balance of this card, " +
                            "enter 2 to cashout money to card," + " enter 3 to add money to card: ");
                    StartMessageDisplayed = true;
                }
                System.out.println(retrievedCard.getAmountOfMoney());
                int SelectOperation = reader.nextInt();

                if (SelectOperation == 1){
                    System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
                    ATMModeSelectedCorrectly = true;
                }
                else if (SelectOperation == 2){
                    retrievedCard = ATM.CashoutFromCard(retrievedCard, retrievedBool, atm);
                    list.set(index, retrievedCard);
                    ATMModeSelectedCorrectly = true;
                }
                else if (SelectOperation == 3){
                    retrievedCard = ATM.AddMoneyToCard(retrievedCard, retrievedBool, atm);
                    list.set(index, retrievedCard);
                    System.out.println(retrievedCard.getAmountOfMoney());
                    ATMModeSelectedCorrectly = true;
                }
                else{
                    System.out.println("You've selected incorrect mode of ATM. Supported ones are '1','2' or '3'. Please try again: ");
                    SelectOperation = 0;
                    SelectOperation = reader.nextInt();
                }
            }
            else{
                System.out.println("\n Credit card is blocked, you don't have access to any ATM operations");
                list.set(index, retrievedCard);
                break;
            }
        }


        String[] list1 = WorkWithData.readObjectArrayIntoStringArray(list,atm);
        WorkWithData.overwriteFile(filePath2, list1);
        /* prototype with option to continue work with ATM

        String ExitfromATMstr1 = "";
        boolean ExitfromATMstr2 = false;
        String[] boolarr = new String[1];
        while ((ATMModeSelectedCorrectly == false) && (ExitfromATMstr2 == false)){
            ExitfromATMstr2 = Boolean.valueOf(ExitfromATMstr1);
            System.out.println("STR2: " + ExitfromATMstr2);
            if (retrievedBool == true){
                if (StartMessageDisplayed == false){
                    System.out.println("\nEnter 1 to view balance of this card, " +
                            "enter 2 to cashout money to card," + " enter 3 to add money to card: ");
                    StartMessageDisplayed = true;
                }
                ExitfromATMstr1 = boolarr[0];
                System.out.println("STR1: " + ExitfromATMstr1);
                int SelectOperation = reader.nextInt();

                if (SelectOperation == 1){

                    System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
                    ATMModeSelectedCorrectly = true;

                    System.out.println("Do you wish to exit ATM? Type 'y' to exit ATM, type 'n' to continue: ");
                    ExitfromATMstr = reader1.nextLine();
                    if (ExitfromATMstr.equals("y")){
                        ExitfromATM = true;
                    }
                    else if (ExitfromATMstr.equals("n")){
                        ExitfromATM = false;
                    }
                    boolarr[0] = String.valueOf(ExitfromATM);
                    System.out.println("Boolarr: " + boolarr[0]);
                }
                else if (SelectOperation == 2){
                    ATM.CashoutFromCard(retrievedCard, retrievedBool, atm);
                    ATMModeSelectedCorrectly = true;

                    System.out.println("Do you wish to exit ATM? Type 'y' to exit ATM, type 'n' to continue: ");
                    ExitfromATMstr = reader1.nextLine();
                    if (ExitfromATMstr.equals("y")){
                        ExitfromATM = true;
                    }
                    else if (ExitfromATMstr.equals("n")){
                        ExitfromATM = false;
                    }
                    boolarr[0] = String.valueOf(ExitfromATM);
                    System.out.println("Boolarr: " + boolarr[0]);
                }
                else if (SelectOperation == 3){
                    ATM.AddMoneyToCard(retrievedCard, retrievedBool, atm);
                    ATMModeSelectedCorrectly = true;

                    System.out.println("Do you wish to exit ATM? Type 'y' to exit ATM, type 'n' to continue: ");
                    ExitfromATMstr = reader1.nextLine();
                    if (ExitfromATMstr.equals("y")){
                        ExitfromATM = true;
                    }
                    else if (ExitfromATMstr.equals("n")){
                        ExitfromATM = false;
                    }
                    boolarr[0] = String.valueOf(ExitfromATM);
                    System.out.println("Boolarr: " + boolarr[0]);
                }
                else{
                    System.out.println("You've selected incorrect mode of ATM. Supported ones are '1','2' or '3'. Please try again: ");
                    SelectOperation = 0;
                    SelectOperation = reader.nextInt();
                }
            }
            else{
                System.out.println("\n Credit card is blocked, you don't have access to any operations");
                break;
            }
        }
         */
    }
}