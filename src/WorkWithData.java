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
}
