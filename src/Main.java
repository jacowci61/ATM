import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


public class Main {

    public static void main(String[] args) {

        String filePathTEST = "src/ATMDataTEST.txt";

        try {
            String[] lines = WorkWithData.readFileIntoArray(filePathTEST);

            System.out.println("\n");
            List<BankAccount> BankAccountsList = new ArrayList<BankAccount>(lines.length);
            List<CreditCard> CreditCardsList = new ArrayList<CreditCard>(lines.length);

            for (String line : lines) {
                //System.out.println("Lines in txt file are: " + line);
                String[] TempObject = line.split("\\s+");
                BankAccount data1 = new BankAccount(TempObject[0], Double.parseDouble(TempObject[1]));
                BankAccountsList.add(data1);

                CreditCard data2 = new CreditCard(data1, Long.parseLong(TempObject[2]), Integer.parseInt(TempObject[3]), Boolean.parseBoolean(TempObject[4]));
                CreditCardsList.add(data2);
            }

            String query = "4789566628948954";
            CreditCard result = WorkWithData.findElementContainingSequence(CreditCardsList, query);
            int index = CreditCardsList.indexOf(result);
            System.out.println("Result is: " + result.getBankAccountID() + " " + result.getCreditCardNumber() + " " + result.getAmountOfMoney() + " Index is: " + index);


            WorkWithData.overwriteFile(filePathTEST, lines);
            System.out.println("\n");
            System.out.println("File overwritten successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}