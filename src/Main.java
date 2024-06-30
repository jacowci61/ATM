import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) {

        File directory = new File("./");
        System.out.println("path is: " + directory.getAbsolutePath());

        String filePath = "src/ATMData.txt";
        String filePathTEST = "src/ATMDataTEST.txt";

        // Using BufferedReader to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePathTEST))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BankAccount bankAccount1 = new BankAccount("u123ADDED",2550.0);
        CreditCard creditCard1 = new CreditCard(bankAccount1,4111522263337444L, 1024, false);

        String str1 = creditCard1.getBankAccountID() + " " + creditCard1.getAmountOfMoney() + " " + creditCard1.getCreditCardNumber() + " " + creditCard1.getPIN() + " " + creditCard1.getIsCreditCardBlocked();


        /* appending content to .txt

        String contentToAppend = str1;
        try (FileWriter fileWriter = new FileWriter(filePathTEST, true)) {
            fileWriter.write(System.lineSeparator() + contentToAppend);
            System.out.println("Content has been appended successfully.\n ");
        } catch (IOException e) {
            e.printStackTrace();
        }

         */


        try {
            String[] lines = readFileIntoArray(filePathTEST);
            for (String line : lines) {
                System.out.println("Lines in txt file are: " + line);
            }
            String strTEST = lines[0];
            System.out.println(strTEST);
            String[] TempObject = strTEST.split("\\s+");
            for (String object : TempObject){
                System.out.println("Properties: " + object);
            }

            String query = "4789566628948954";
            String result = findElementContainingSequence(lines, query);

            if (result != null) {
                System.out.println("Matching element: " + result);
            } else {
                System.out.println("No matching element found.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /* blueprint for checking date of blocked card

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Current date: " + formattedDate);
         */
    }


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


    public static String findElementContainingSequence(String[] data, String query) {
        for (String element : data) {
            if (element.contains(query)) {
                return element;
            }
        }
        return null;
    }
/*  overwriting file

    public class OverwriteFileWithArray {
        public static void main(String[] args) {
            String filePath = "example.txt";
            String[] linesToWrite = {
                    "This is the first line.",
                    "This is the second line.",
                    "This is the third line."
            };

            try {
                overwriteFileWithArray(filePath, linesToWrite);
                System.out.println("File has been overwritten successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void overwriteFileWithArray(String filePath, String[] lines) throws IOException {
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                for (String line : lines) {
                    fileWriter.write(line + System.lineSeparator());
                }
            }
        }
    }

 */
}