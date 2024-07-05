import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "src/ATMDataTEST.txt";

        long UserCard = WorkWithData.readInputtedCreditCard();

        Map<String,Object> map = ATM.Authorization(UserCard, filePath);

        CreditCard retrievedCard = (CreditCard) map.get("credit card");
        System.out.println("Balance value on this card: " + retrievedCard.getAmountOfMoney());
        boolean retrievedBool = (boolean) map.get("bool");

        Scanner reader = new Scanner(System.in);
        System.out.println("\n Enter 1 to cashout from card," +
                " enter 2 to add money to card: ");
        int SelectOperation = reader.nextInt();

        if (SelectOperation == 1){
            ATM.CashoutFromCard(retrievedCard, retrievedBool);
        }
        else if (SelectOperation == 2){
            ATM.AddMoneyToCard(retrievedCard, retrievedBool);
        }
        //ATM.HandleCardBalance(ATM.CheckCardBalance(retrievedCard, retrievedBool));
    }
}