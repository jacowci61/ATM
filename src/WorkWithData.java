import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithData {
    public static String[] readFileIntoArray(String filePath) throws IOException {
        List<String> linesList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        }
        return linesList.toArray(new String[0]);
    }

    public static CreditCard findElementContainingSequence(List<CreditCard> CreditCardsList, String query) {
        for (CreditCard creditCard : CreditCardsList) {
            if (String.valueOf(creditCard.getCreditCardNumber()).contains(query)) {
                return creditCard;
            }
        }
        return null;
    }

    public static void overwriteFile(String filePath, String[] data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static List<CreditCard> readStringArrayIntoObjectArray(String filePath) {


        List<CreditCard> CreditCardsList = null;
        try {
            String[] lines = WorkWithData.readFileIntoArray(filePath);

            System.out.println("\n");
            CreditCardsList = new ArrayList<CreditCard>(lines.length);

            for (String line : lines) {
//              System.out.println("Lines in txt file are: " + line);
                String[] TempObject = line.split("\\s+");
                BankAccount data1 = new BankAccount(TempObject[0], Double.parseDouble(TempObject[1]));

                CreditCard data2 = new CreditCard(data1, Long.parseLong(TempObject[2]), Integer.parseInt(TempObject[3]), Boolean.parseBoolean(TempObject[4]));
                CreditCardsList.add(data2);
            }

            String query = "4789566628948954";
            CreditCard result = WorkWithData.findElementContainingSequence(CreditCardsList, query);
            int index = CreditCardsList.indexOf(result);
            System.out.println("Result is: " + result.getBankAccountID() + " " + result.getCreditCardNumber() + " " + result.getAmountOfMoney() + " Index is: " + index);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return CreditCardsList;
    }
}
