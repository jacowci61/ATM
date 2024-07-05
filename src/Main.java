import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "src/ATMDataTEST.txt";
        List<CreditCard> list = WorkWithData.readStringArrayIntoObjectArray(filePath);
        CreditCard cr1 = list.getFirst();
        ATM atm = new ATM(cr1.getBankAccountID(), cr1.getAmountOfMoney());
        list.remove(0);


        long UserCard = WorkWithData.readInputtedCreditCard();

        Map<String,Object> map = ATM.Authorization(UserCard, filePath);

        CreditCard retrievedCard = (CreditCard) map.get("credit card");
        boolean retrievedBool = (boolean) map.get("bool");


        if (retrievedBool == true){
            System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
            Scanner reader = new Scanner(System.in);
            System.out.println("\n Enter 1 to cashout from card," +
                    " enter 2 to add money to card: ");
            int SelectOperation = reader.nextInt();

            if (SelectOperation == 1){
                ATM.CashoutFromCard(retrievedCard, retrievedBool, atm);
            }
            else if (SelectOperation == 2){
                ATM.AddMoneyToCard(retrievedCard, retrievedBool, atm);
            }
        }
        else{
            System.out.println("Credit card is blocked, you don't have access to any operations");
        }


        //ATM.HandleCardBalance(ATM.CheckCardBalance(retrievedCard, retrievedBool));
    }
}