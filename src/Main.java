import java.io.File;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.List;

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

        String contentToAppend = str1;
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(System.lineSeparator() + contentToAppend);
            System.out.println("Content has been appended successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* blueprint for checking date of blocked card
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Current date: " + formattedDate);
         */
    }
}