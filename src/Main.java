import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


public class Main {

    public static void main(String[] args) {

        File directory = new File("./");
        System.out.println("\n");
        System.out.println("path is: " + directory.getAbsolutePath());

        String filePath = "src/ATMData.txt";
        String filePathTEST = "src/ATMDataTEST.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePathTEST))) {
            String line;
            System.out.println("\n");
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BankAccount bankAccount1 = new BankAccount("u123ADDED", 2550.0);
        CreditCard creditCard1 = new CreditCard(bankAccount1, 4111522263337444L, 1024, false);

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

            for (int i = 0; i < BankAccountsList.size(); i++) {
                System.out.println(BankAccountsList.get(i).toString());
            }
            for (int i = 0; i < CreditCardsList.size(); i++) {
                System.out.println(CreditCardsList.get(i).getCreditCardNumber());
            }

            String strTEST = lines[0];
            System.out.println("\n");
            System.out.println(strTEST);
            String[] TempObject = strTEST.split("\\s+");

            for (String object : TempObject) {
                System.out.println("Properties: " + object);
            }

            //add reading user input here. Credit card number can be entered with spaces or not, doesnt matter.
            //use handling input from b64 converter/somewhere in other project on github


            String query = "4111522263337445";
            CreditCard result = findElementContainingSequence(CreditCardsList, query);
            System.out.println("Result is: " + result.getBankAccountID() + " " + result.getCreditCardNumber() + " " + result.getAmountOfMoney());
            /* direct .txt data operations

            String query = "4111522263337445";
            String result = findElementContainingSequence(CreditCardsList, query);
            int IndexOfResult = Arrays.asList(lines).indexOf(result);
            System.out.println("Result is:" + result + "; and extraction is " + lines[IndexOfResult]);
            // unclear whether to directly modify object, skipping finding precise string from lines
            // and just keeping the index of found string
            // see [[note# already read objects handling]]
            String[] TempObject2 = result.split("\\s+");
            // if:
            // either card balance changed
            // or card becomes blocked
            // store these two methods in their respective classes+setters?

            // TempObject2[1] = where i face problems. Maybe each string in lines[] should be
            // constructed to an object so i can use setters. But that's unneeded dealing
            // with IO-operations. Can make two variants, for now it'll be a direct rewrite w/o setters
            // On a second thought - why use lines[]? Because its easiest way to handle searching for element

            String str2 =creditCard1.getAmountOfMoney() + " " + creditCard1.getIsCreditCardBlocked();
            System.out.println("\nUnedited object: " + str2);
            System.out.println("\n");

            for (String object : TempObject2) {
                System.out.println("Before: " + object);
            }
            StringJoiner joiner1 = new StringJoiner(" ");
            for (String object : TempObject2) {
                joiner1.add(object);
            }

            TempObject2[1] = String.valueOf(4900.0);
            TempObject2[4] = String.valueOf(true); // setting that toggles ifcardisblocked

            System.out.println("\n");
            for (String object : TempObject2) {
                System.out.println("After: " + object);
            }

            StringJoiner joiner = new StringJoiner(" ");
            for (String object : TempObject2) {
                joiner.add(object);
            }
            String originalString = joiner1.toString();
            System.out.println("ORIGINAL STRING: " + originalString);
            String replacementString = joiner.toString();
            System.out.println("JOINED STRING: " + replacementString);

            for (String line : lines) {
                System.out.println("Lines in txt file are: " + line);
            }
            lines[IndexOfResult] = replacementString;
            for (String line : lines) {
                System.out.println("REPLACED lines in txt file are: " + line);
            }


            creditCard1.setAmountOfMoney(Double.parseDouble(TempObject2[1]));
            creditCard1.setIsCreditCardBlocked(Boolean.valueOf(TempObject2[4]));
            String str21 = creditCard1.getAmountOfMoney() + " " + creditCard1.getIsCreditCardBlocked();
            System.out.println("\nEdited object: " + str21);

            System.out.println("\n");
            if (result != null) {
                System.out.println("Matching element: " + result);
            } else {
                System.out.println("No matching element found.");
            }
            */


            // problem here is that i dont replace data in lists
            // so, before calling a setter, find a string with creditcard number to be replaced and store rplcmnt = index of this string
            // .split("\\s+"), replace needed parameters of object, concat and replace lines[rplcmt] with newly concated string
            creditCard1.setPIN(9876);
            creditCard1.setCreditCardNumber(1111222233334444L);
            //String str2 = creditCard1.getBankAccountID() + " " + creditCard1.getAmountOfMoney() + " " + creditCard1.getCreditCardNumber() + " " + creditCard1.getPIN() + " " + creditCard1.getIsCreditCardBlocked();
            //System.out.println("\n" + str2);

            overwriteFile(filePathTEST, lines);
            System.out.println("\n");
            System.out.println("File overwritten successfully.");
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

    /* this method was used for direct .txt search

    public static String findElementContainingSequence(String[] data, String query) {
        for (String element : data) {
            if (element.contains(query)) {
                return element;
            }
        }
        return null;
    }
     */

    public static void overwriteFile(String filePath, String[] data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}