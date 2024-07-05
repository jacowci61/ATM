import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkWithData {
    public static String[] readFileIntoStringArray(String filePath) throws IOException {
        List<String> linesList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
        }
        return linesList.toArray(new String[0]);
    }

    public static List<CreditCard> readStringArrayIntoObjectArray(String filePath) {

        List<CreditCard> CreditCardsList = null;
        try {
            String[] lines = WorkWithData.readFileIntoStringArray(filePath);

            CreditCardsList = new ArrayList<CreditCard>(lines.length);

            boolean firstIteration = true;
            for (String line : lines) {
                if (firstIteration){
                    String[] TempObject = line.split("\\s+");
                    ATM Atm = new ATM(TempObject[0],Double.parseDouble(TempObject[1]));
                    BankAccount data1 = new BankAccount(TempObject[0], Double.parseDouble(TempObject[1]));

                    firstIteration = false;

                    CreditCard data2 = new CreditCard(data1, 1L, 3897, true);
                    CreditCardsList.add(data2);
                }
                else{
                    //System.out.println("Lines in txt file are: " + line);
                    String[] TempObject = line.split("\\s+");
                    BankAccount data1 = new BankAccount(TempObject[0], Double.parseDouble(TempObject[1]));

                    CreditCard data2 = new CreditCard(data1, Long.parseLong(readInputtedCreditCard(TempObject[2],true)), Integer.parseInt(TempObject[3]), Boolean.parseBoolean(TempObject[4]));
                    CreditCardsList.add(data2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CreditCardsList;
    }

    public static String[] readObjectArrayIntoStringArray(List<CreditCard> list, ATM atmInstance) {
        List<CreditCard> CreditCardsList = list;
        ATM atm = atmInstance;
        BankAccount atmElement1 = new BankAccount(atm.getATMID(), atm.getCashAvailableInATM());
        CreditCard atmElement = new CreditCard(atmElement1, 0, 0, false);
        list.add(0, atmElement);
        list.add(0, atmElement);
        String[] lines = new String[list.size()];
        for (int i = 0; i <= lines.length; ++i) {
            if (i == 0){
                String line = CreditCardsList.get(i).getBankAccountID() + " " +  CreditCardsList.get(i).getAmountOfMoney();
                lines[i] = line;
            }
            else{
                String line = CreditCardsList.get(i).getBankAccountID() + " " +  CreditCardsList.get(i).getAmountOfMoney() + " " +
                        CreditCardsList.get(i).getCreditCardNumber() + " " + CreditCardsList.get(i).getPIN() + " " +
                        CreditCardsList.get(i).getIsCreditCardBlocked();
                lines[i] = line;
            }
        }
        return lines;
    }

    public static CreditCard findElementContainingSequence(List<CreditCard> CreditCardsList, String query) {
        for (CreditCard creditCard : CreditCardsList) {
            if (String.valueOf(creditCard.getCreditCardNumber()).contains(query)) {
                return creditCard;
            }
        }
        return null; // TO FIX: throw error if element not found
    }

    public static void overwriteFile(String filePath, String[] list) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            try {
                for (String line : list) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String readInputtedCreditCard(String RequestedCardtemp, boolean IsFromFile){
        Scanner reader = new Scanner(System.in);
        String[] rq1;
        boolean a1 = true;
        if (IsFromFile == false){
            while(a1){
                rq1 = RequestedCardtemp.split("[ -]");
                RequestedCardtemp = "";
                for (String line : rq1){
                    RequestedCardtemp += line;
                }
                if (RequestedCardtemp.length() == 16){
                    a1 = false;
                }
                else{
                    RequestedCardtemp = "";
                    rq1 = null;
                    System.out.println("Incorrect creditcard number entered. Enter a correct 16-number creditcard number: ");

                    RequestedCardtemp = reader.nextLine();
                    rq1 = RequestedCardtemp.split("[ -]");

                    RequestedCardtemp = "";
                    for (String line : rq1){
                        RequestedCardtemp += line;
                    }
                }
            }
        }
        else if (IsFromFile == true){
            while(a1){
                rq1 = RequestedCardtemp.split("[ -]");
                RequestedCardtemp = "";
                for (String line : rq1){
                    RequestedCardtemp += line;
                }
                if (RequestedCardtemp.length() == 16){
                    a1 = false;
                }
                else{
                    System.out.println("Error in reading .txt file");
                    //write handling smh. Maybe through optional parameters of this method, idk
                    /*
                    RequestedCardtemp = "";
                    rq1 = null;
                    System.out.println("Incorrect creditcard number entered. Enter a correct 16-number creditcard number: ");

                    RequestedCardtemp = reader.nextLine();
                    rq1 = RequestedCardtemp.split("[ -]");

                    RequestedCardtemp = "";
                    for (String line : rq1){
                        RequestedCardtemp += line;
                    }

                     */
                }
            }
        }

        //long RequestedCard = Long.parseLong(RequestedCardtemp);
        //return RequestedCard;
        return RequestedCardtemp;
    }
}
