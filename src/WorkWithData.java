import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WorkWithData {
    public static String[] readFileIntoStringArray(String filePath) throws IOException {

        List<String> stringArray = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringArray.add(line);
            }
        }
        return stringArray.toArray(new String[0]);
    }

    public static List<CreditCard> readStringArrayIntoObjectArray(String filePath) {

        List<CreditCard> CreditCardsList = null;
        try {
            String[] stringArrayWithDataFromFile = WorkWithData.readFileIntoStringArray(filePath);

            CreditCardsList = new ArrayList<CreditCard>(stringArrayWithDataFromFile.length);

            boolean firstIteration = true;

            for (String line : stringArrayWithDataFromFile) {

                if (firstIteration){

                    firstIteration = false;

                    String tempDataAsString = "01-01-1970 00:00:00";
                    LocalDateTime tempDate = LocalDateTime.parse(tempDataAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                    String[] tempObject = line.split("\\s+");
                    BankAccount tempBankAccount = new BankAccount(tempObject[0], Double.parseDouble(tempObject[1]));
                    CreditCard ATMDataAsCreditCard = new CreditCard(tempBankAccount, 1L, 1111, false, tempDate);

                    CreditCardsList.add(ATMDataAsCreditCard);
                }
                else{

                    String[] tempObject = line.split("\\s+");

                    String tempDateAsString = tempObject[5] + " " + tempObject[6];
                    LocalDateTime tempDate = LocalDateTime.parse(tempDateAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                    BankAccount tempBankAccount = new BankAccount(tempObject[0], Double.parseDouble(tempObject[1]));
                    CreditCard creditCard = new CreditCard(tempBankAccount, Long.parseLong(readInputtedCreditCard(tempObject[2],true)),
                            Integer.parseInt(tempObject[3]), Boolean.parseBoolean(tempObject[4]), tempDate);
                    CreditCardsList.add(creditCard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CreditCardsList;
    }

    public static String[] readObjectArrayIntoStringArray(List<CreditCard> listOfCreditCards, ATM atmInstance) {

        List<CreditCard> CreditCardsList = listOfCreditCards;
        ATM atm = atmInstance;

        String tempDateAsString = "01-01-1970 00:00:00";
        LocalDateTime tempDate = LocalDateTime.parse(tempDateAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        BankAccount tempBankAccount = new BankAccount(atm.getATMID(), atm.getCashAvailableInATM());
        CreditCard ATMDataAsCreditCard = new CreditCard(tempBankAccount, 1L, 1111, false, tempDate);

        listOfCreditCards.add(0, ATMDataAsCreditCard);
        String[] stringArrayOfCreditCards = new String[listOfCreditCards.size()];

        for (int i = 0; i < stringArrayOfCreditCards.length; ++i) {

            if (i == 0){

                String line = CreditCardsList.get(i).getBankAccountID() + " " +  CreditCardsList.get(i).getAmountOfMoney();
                stringArrayOfCreditCards[i] = line;
            }
            else{

                String line = CreditCardsList.get(i).getBankAccountID() + " " +  CreditCardsList.get(i).getAmountOfMoney() + " " +
                        changeCreditCardIDFormat(CreditCardsList.get(i).getCreditCardID()) + " " + CreditCardsList.get(i).getCreditCardPIN() + " " +
                        CreditCardsList.get(i).getIsCreditCardBlocked() + " " +
                        CreditCardsList.get(i).getDateOfCreditCardBlock().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                stringArrayOfCreditCards[i] = line;
            }
        }
        return stringArrayOfCreditCards;
    }

    public static void overwriteFileWithStringArray(String filePath, String[] stringArray) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            try {
                for (String line : stringArray) {

                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("File overwritten with new data successfully.");
    }

    public static String readInputtedCreditCard(String inputtedCreditCard, boolean readFromFile){
        Scanner reader = new Scanner(System.in);
        String[] requestedCard;
        boolean loopBreak = false;
        if (readFromFile == false){

            while(loopBreak == false){
                requestedCard = inputtedCreditCard.split("[ -]");
                inputtedCreditCard = "";

                for (String line : requestedCard){
                    inputtedCreditCard += line;
                }

                if (inputtedCreditCard.length() == 16){
                    loopBreak = true;
                }
                else{
                    inputtedCreditCard = "";
                    requestedCard = null;
                    System.out.println("Incorrect credit card number entered. Enter a correct 16-number credit card number: ");

                    inputtedCreditCard = reader.nextLine();
                    requestedCard = inputtedCreditCard.split("[ -]"); // split either by space or dash

                    inputtedCreditCard = "";
                    for (String line : requestedCard){
                        inputtedCreditCard += line;
                    }
                }
            }
        }
        else if (readFromFile == true){

            while(loopBreak == false){

                requestedCard = inputtedCreditCard.split("[ -]");
                inputtedCreditCard = "";

                for (String line : requestedCard){
                    inputtedCreditCard += line;
                }

                if (inputtedCreditCard.length() == 16){
                    loopBreak = true;
                }
                else{
                    System.out.println("Error in reading .txt file");
                }
            }
        }
        return inputtedCreditCard;
    }

    public static Map<String,Object> findCreditCardByNumber(List<CreditCard> listOfCreditCards, String requestedCreditCardID) {

        for (CreditCard creditCard : listOfCreditCards) {

            if (String.valueOf(creditCard.getCreditCardID()).contains(requestedCreditCardID)) {

                Map<String,Object> CreditCardObjectAndItsIndex = new HashMap<>();
                CreditCardObjectAndItsIndex.put("credit card", creditCard);
                CreditCardObjectAndItsIndex.put("index", listOfCreditCards.indexOf(creditCard));
                return CreditCardObjectAndItsIndex;
            }
        }
        return null;
    }

    public static boolean isCreditCardBlockTimeExceeded(CreditCard creditCard){
        boolean blockTimeExceeded;
        if (LocalDateTime.now().isAfter(creditCard.getDateOfCreditCardBlock()))
        {
            blockTimeExceeded = true;
        }
        else{
            blockTimeExceeded = false;
        }
        return blockTimeExceeded;
    }

    public static String changeCreditCardIDFormat(long creditCardID){

        String passedData = String.valueOf(creditCardID);
        String final1 = "";
        int dashCounter = 0; // used to trigger addition of dash between 4 digits of ID of credit card


        for (int i = 0; i < passedData.length(); ++i){
            dashCounter = ++dashCounter;
            if (dashCounter == 4 && i != passedData.length()-1){ // second condition is to avoid adding dash in the end of ID of credit card
                final1 += (passedData.charAt(i) + "-");
                dashCounter = 0;
            }
            else{
                final1 += String.valueOf(passedData.charAt(i));
            }
        }
        return final1;
    }
}