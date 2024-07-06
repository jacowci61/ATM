import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ATM {

    private double CashAvailableInATM;
    private String ATMID;

    public ATM(String ATM, double CashAvailableInATM){
        this.ATMID = ATM;
        this.CashAvailableInATM = CashAvailableInATM;
    }

    public void setCashAvailableInATM(double getCashAvailableInATM){
        this.CashAvailableInATM = CashAvailableInATM;
    }

    public double getCashAvailableInATM(){
        return CashAvailableInATM;
    }

    public void setATMID(String ATM){
        this.ATMID = ATM;
    }

    public String getATMID(){
        return ATMID;
    }


    public static Map<String,Object> Authorization(long UserCreditCard, String filePath){

        boolean UserIsAuthorized = false;
        boolean CardUnlocked = false;

        String query = String.valueOf(UserCreditCard);
        List<CreditCard> CreditCardsList = WorkWithData.readStringArrayIntoObjectArray(filePath);
        CreditCard result = (CreditCard) WorkWithData.findElementContainingSequence(CreditCardsList, query).get("credit card");
        Map<String,Object> CreditCardMap = new HashMap<>();

        if ((result.getIsCreditCardBlocked() == true) && (WorkWithData.blockTimeExceeded(result) == true)){
            result.setIsCreditCardBlocked(false);
            result.setDate(LocalDate.of(1970, Month.JANUARY, 1).atStartOfDay());
            CardUnlocked = true;
            System.out.println("Your card is now unlocked.");
        }
        else if ((result.getIsCreditCardBlocked() == true) && (WorkWithData.blockTimeExceeded(result) != true)){
            System.out.println("Your card is still blocked. It will be unblocked at " + result.getDate().plusDays(1).plusSeconds(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            CreditCardMap.put("credit card", result);
            CreditCardMap.put("bool", UserIsAuthorized);
            return CreditCardMap;
        }
        if ((result.getIsCreditCardBlocked() == false) || (CardUnlocked == true)) {
            int RequiredPIN = result.getPIN();
            int i = 1;
            int attempts;
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter a PIN: ");
            int PIN = reader.nextInt();

            while (true){

                if (PIN == RequiredPIN){
                    System.out.println("Correct PIN entered.");
                    UserIsAuthorized = true;
                    break;
                }
                else if ((PIN != RequiredPIN) && (i < 3)){
                    attempts = 3 - i;
                    System.out.println("Incorrect PIN entered. You have " +  attempts + " attempt(s) left.");
                    System.out.println("Enter a PIN: ");
                    PIN = reader.nextInt();
                    i = ++i;
                }
                else{
                    System.out.println("3 consequent incorrect PINs entered. Credit card blocked until *insert date here*");
                    UserIsAuthorized = false;
                    result.setIsCreditCardBlocked(true);
                    break;
                }
            }
            //int index = CreditCardsList.indexOf(result);

            CreditCardMap.put("credit card", result);
            CreditCardMap.put("bool", UserIsAuthorized);
            return CreditCardMap;
        }
        return CreditCardMap;
    }

    public static void CheckCardBalance(CreditCard UserCreditCard, boolean UserIsAuthorized){
        double CardBalance = UserCreditCard.getAmountOfMoney();
        if (UserIsAuthorized == true){
            System.out.println("Card balance is: " + CardBalance);
        }
        else{
            System.out.println("Cannot check card balance, card is blocked");
        }
    }

    public static CreditCard CashoutFromCard(CreditCard UserCreditCard, boolean UserIsAuthorized, ATM atm){
        double CardBalance = UserCreditCard.getAmountOfMoney();

        if (UserIsAuthorized == true){
            if (CardBalance == 0.0){
                System.out.println("Creditcard balance is 0, you can't proceed with cashout.");
            }
            else{
                Scanner reader = new Scanner(System.in);
                System.out.println("\n Enter amount of money you want to cashout: ");
                //string to double, so it can read even numbers like "100" without the "100.0"
                Double CashoutValue = Double.parseDouble(reader.nextLine());

                while (true){
                    if ((CashoutValue <= atm.getCashAvailableInATM()) && (CashoutValue <= CardBalance)){
                        break;
                    }
                    else if (CashoutValue > atm.getCashAvailableInATM()){
                        System.out.println("ATM doesn't have this amount of money, maximum amount is: "
                                + atm.getCashAvailableInATM() + " Please select another value: ");
                        CashoutValue = Double.parseDouble(reader.nextLine());
                    }
                    else if (CashoutValue > CardBalance){
                        System.out.println("Creditcard doesn't have this amount of money, it's balance is: "
                                + UserCreditCard.getAmountOfMoney() + " Please select another value: ");
                        CashoutValue = Double.parseDouble(reader.nextLine());
                    }
                }
                UserCreditCard.changeBalance(UserCreditCard.getAmountOfMoney()-CashoutValue);
                atm.setATMBalance(atm.getCashAvailableInATM() - CashoutValue);
                System.out.println("ATM balance: " + atm.getCashAvailableInATM() + ", Card balance: " + UserCreditCard.getAmountOfMoney());
                return UserCreditCard;
            }
        }
        else{
            System.out.println("Cannot cashout from card, card is blocked");
        }
        return UserCreditCard;
    }

    public static CreditCard AddMoneyToCard(CreditCard UserCreditCard, boolean UserIsAuthorized, ATM atm){
        double CardBalance = UserCreditCard.getAmountOfMoney();

        if (UserIsAuthorized == true){

            Scanner reader = new Scanner(System.in);
            System.out.println("\n Enter amount of money you want to add to your balance: ");
            //string to double, so it can read even numbers like "100" without the "100.0"
            Double AddMoneyToCardValue = Double.parseDouble(reader.nextLine());

            while (true){
                if (AddMoneyToCardValue <= 1000000.0){
                    break;
                }
                else if (AddMoneyToCardValue > 1000000.0){
                    System.out.println("ATM doesn't support this amount of money, maximum amount is: 1 000 000."
                            + " Please select another value: ");
                    AddMoneyToCardValue = Double.parseDouble(reader.nextLine());
                }
            }

            UserCreditCard.changeBalance(UserCreditCard.getAmountOfMoney() + AddMoneyToCardValue);
            atm.setATMBalance(atm.getCashAvailableInATM() + AddMoneyToCardValue);
            System.out.println("ATM balance: " + atm.getCashAvailableInATM() + ", Card balance: " + UserCreditCard.getAmountOfMoney());
            return UserCreditCard;
        }
        else{
            System.out.println("Cannot add money to card, card is blocked");
        }
        return UserCreditCard;
    }

    public double getATMBalance(){
        return CashAvailableInATM;
    }
    public void setATMBalance(double NewValueOfCashAvailableInATM){
        CashAvailableInATM = NewValueOfCashAvailableInATM;
    }
    /*
    public static void HandleCardBalance(OptionalDouble CardBalance){
        try {
            double balanceOrThrow = CardBalance.orElseThrow(() -> new IllegalStateException("Card is blocked"));
            System.out.println("Card balance: " + balanceOrThrow);
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
     */
}