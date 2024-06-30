import java.io.File;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) {

        File directory = new File("./");
        System.out.println("path is: " + directory.getAbsolutePath());

        String filePath = "src/ATMData.txt";

        // Using BufferedReader to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BankAccount bankAccount1 = new BankAccount("u123",2550.0);
        CreditCard creditCard1 = new CreditCard(bankAccount1,4111522263337444L, 1024, false);

        String str1 = creditCard1.getBankAccountID() + " " + creditCard1.getAmountOfMoney() + " " + creditCard1.getCreditCardNumber() + " " + creditCard1.getPIN() + " " + creditCard1.getIsCreditCardBlocked();

        /* writing to a file

        String contentToAppend = str1;
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(System.lineSeparator() + contentToAppend);
            System.out.println("Content has been appended successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Long.toString(creditCard1.getCreditCardNumber()));
        */

        String textToFind = "4789522228948954";
        String[] ObjectData;
        try {
            String str2 = FindLineContainingText.findLineContainingText(filePath, textToFind);
            ObjectData = str2.split("\\s+");
            for (String data : ObjectData){
                System.out.println(data);
            }
            BankAccount bankAccount2 = new BankAccount(ObjectData[0],Double.parseDouble(ObjectData[1]));
            CreditCard creditCard2 = new CreditCard(bankAccount2, Long.parseLong(ObjectData[2]), Integer.parseInt(ObjectData[3]), Boolean.parseBoolean(ObjectData[4]));
            System.out.println("Object data is: " + bankAccount2.getBankAccountID() + " " + bankAccount2.getAmountOfMoney() + " " + creditCard2.getCreditCardNumber() + " " + creditCard2.getPIN() + " " + creditCard2.getIsCreditCardBlocked());
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

    public class FindLineContainingText {
        public static void main(String[] args) {
            String filePath = "example.txt";
            String textToFind = "specific text";

            try {
                String matchingLine = findLineContainingText(filePath, textToFind);
                if (matchingLine != null) {
                    System.out.println("Matching line: " + matchingLine);
                } else {
                    System.out.println("No matching line found.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String findLineContainingText(String filePath, String textToFind) throws IOException {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(textToFind)) {
                        return line;
                    }
                }
            }
            return null; // Return null if no matching line is found
        }
    }
}