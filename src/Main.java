import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

    String filePath = "src/ATMDataTEST.txt";

    long UserCard = WorkWithData.readInputtedCreditCard();

    Map<String,Object> map = ATM.Authorization(UserCard, filePath);
    CreditCard retrievedCard = (CreditCard) map.get("credit card");
    boolean retrievedBool = (boolean) map.get("bool");
    System.out.println(retrievedCard + " " + retrievedBool + " " + retrievedCard.getIsCreditCardBlocked());

    ATM.HandleCardBalance(ATM.CheckCardBalance(retrievedCard, retrievedBool));
    }
}