import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ATM {

    private double cashAvailableInATM;
    private String ATMID;

    public ATM(String ATM, double cashAvailableInATM){
        this.ATMID = ATM;
        this.cashAvailableInATM = cashAvailableInATM;
    }


    public String getATMID(){
        return ATMID;
    }

    public double getCashAvailableInATM(){
        return cashAvailableInATM;
    }


    public void setATMID(String ATM){
        this.ATMID = ATM;
    }

    public void setATMBalance(double newValueOfCashAvailableInATM){
        cashAvailableInATM = newValueOfCashAvailableInATM;
    }


    public static Map<String,Object> creditCardAuthorization(long userCreditCard, String filePath){

        boolean UserIsAuthorized = false;
        boolean cardUnlocked = false;

        Map<String,Object> creditCardObjectAndAuthorizationPermission = new HashMap<>();

        String requestedCreditCard = String.valueOf(userCreditCard);

        List<CreditCard> CreditCardsList = WorkWithData.readStringArrayIntoObjectArray(filePath);
        CreditCard result = (CreditCard) WorkWithData.findCreditCardByNumber(CreditCardsList, requestedCreditCard).get("credit card");

        if ((result.getIsCreditCardBlocked() == true) && (WorkWithData.isCreditCardBlockTimeExceeded(result) == true)){

            result.setIsCreditCardBlocked(false);
            result.setDate(LocalDate.of(1970, Month.JANUARY, 1).atStartOfDay());
            cardUnlocked = true;
            System.out.println("24h since last block are passed, your card is now unlocked.");
        }
        else if ((result.getIsCreditCardBlocked() == true) && (WorkWithData.isCreditCardBlockTimeExceeded(result) != true)){

            System.out.println("Your card is still blocked. It will be unblocked at " +
                    result.getDateOfCreditCardBlock().plusDays(1).plusSeconds(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            creditCardObjectAndAuthorizationPermission.put("credit card", result);
            creditCardObjectAndAuthorizationPermission.put("bool", UserIsAuthorized);
            return creditCardObjectAndAuthorizationPermission;
        }
        if ((result.getIsCreditCardBlocked() == false) || (cardUnlocked == true)) {

            int requiredPIN = result.getCreditCardPIN();
            int i = 1;
            int attempts;

            Scanner reader = new Scanner(System.in);
            System.out.println("Enter a PIN: ");
            int inputtedPIN = reader.nextInt();

            while (true){

                if (inputtedPIN == requiredPIN){
                    System.out.println("Correct PIN entered. You can proceed with ATM operations.");
                    UserIsAuthorized = true;
                    break;
                }
                else if ((inputtedPIN != requiredPIN) && (i < 3)){
                    attempts = 3 - i;
                    System.out.println("Incorrect PIN entered. You have " +  attempts + " attempt(s) left.");
                    System.out.println("Enter a PIN: ");
                    inputtedPIN = reader.nextInt();
                    i = ++i;
                }
                else{
                    System.out.println("3 consequent incorrect PINs entered. Credit card blocked until "
                            + LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                    UserIsAuthorized = false;
                    result.setIsCreditCardBlocked(true);
                    break;
                }
            }

            creditCardObjectAndAuthorizationPermission.put("credit card", result);
            creditCardObjectAndAuthorizationPermission.put("bool", UserIsAuthorized);
            return creditCardObjectAndAuthorizationPermission;
        }
        return creditCardObjectAndAuthorizationPermission;
    }

    public static CreditCard cashoutFromCreditCard(CreditCard userCreditCard, boolean userIsAuthorized, ATM atm){
        double creditCardAmountOfMoney = userCreditCard.getAmountOfMoney();

        if (userIsAuthorized == true){

            if (creditCardAmountOfMoney == 0.0){
                System.out.println("Creditcard balance is 0, you can't proceed with cashout.");
            }
            else{

                Scanner reader = new Scanner(System.in);
                System.out.println("Enter amount of money you want to cashout: ");

                Double cashoutValue = Double.parseDouble(reader.nextLine()); // string to double, so it can read even numbers like "100" without ".0" at the end

                while (true){

                    if ((cashoutValue <= atm.getCashAvailableInATM()) && (cashoutValue <= creditCardAmountOfMoney)){
                        break;
                    }
                    else if (cashoutValue > atm.getCashAvailableInATM()){

                        System.out.println("ATM doesn't have this amount of money, maximum amount available for cashout is: "
                                + atm.getCashAvailableInATM() + " Please enter another value: ");
                        cashoutValue = Double.parseDouble(reader.nextLine());
                    }
                    else if (cashoutValue > creditCardAmountOfMoney){
                        System.out.println("Creditcard doesn't have this amount of money, it's balance is: "
                                + userCreditCard.getAmountOfMoney() + " Please enter another value: ");
                        cashoutValue = Double.parseDouble(reader.nextLine());
                    }
                }

                userCreditCard.setCreditCardBalance(userCreditCard.getAmountOfMoney()-cashoutValue);
                atm.setATMBalance(atm.getCashAvailableInATM() - cashoutValue);
                System.out.println("Remaining credit card balance: " + userCreditCard.getAmountOfMoney());
                return userCreditCard;
            }
        }
        else{
            System.out.println("Cannot cashout from card, card is blocked");
        }
        return userCreditCard;
    }

    public static CreditCard addMoneyToCreditCard(CreditCard userCreditCard, boolean userIsAuthorized, ATM atm){

        if (userIsAuthorized == true){

            Scanner reader = new Scanner(System.in);
            System.out.println("Enter amount of money you want to add to your balance: ");
            //string to double, so it can read even numbers like "100" without the "100.0"
            Double moneyToBeAddedToCreditCard = Double.parseDouble(reader.nextLine());

            while (true){

                if (moneyToBeAddedToCreditCard <= 1000000.0){
                    break;
                }
                else if (moneyToBeAddedToCreditCard > 1000000.0){
                    System.out.println("ATM doesn't support this amount of money, maximum amount is: 1 000 000."
                            + " Please enter another value: ");
                    moneyToBeAddedToCreditCard = Double.parseDouble(reader.nextLine());
                }
            }

            userCreditCard.setCreditCardBalance(userCreditCard.getAmountOfMoney() + moneyToBeAddedToCreditCard);
            atm.setATMBalance(atm.getCashAvailableInATM() + moneyToBeAddedToCreditCard);

            System.out.println("Updated credit card balance: " + userCreditCard.getAmountOfMoney());
            return userCreditCard;
        }
        else{
            System.out.println("Cannot add money to card, card is blocked");
        }
        return userCreditCard;
    }
}