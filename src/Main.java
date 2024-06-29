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

        CreditCard creditCard1 = new CreditCard(4111522263337444L, 1024, false);

        System.out.println(creditCard1.getCreditCardNumber() + " " + creditCard1.getPIN() + " " + creditCard1.getAmountOfMoney() + " " + creditCard1.getIsCreditCardBlocked());
        /* blueprint for checking date of blocked card

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Current date: " + formattedDate);
         */
    }
}