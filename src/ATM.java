import java.util.*;

public class ATM {

    public double CashAvailableInATM = 100000.0;

    public static Map<String,Object> Authorization(long UserCreditCard, String filePath){

        boolean UserIsAuthorized;

        String query = String.valueOf(UserCreditCard);
        List<CreditCard> CreditCardsList = WorkWithData.readStringArrayIntoObjectArray(filePath);
        CreditCard result = WorkWithData.findElementContainingSequence(CreditCardsList, query);
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
        reader.close();

        Map<String,Object> CreditCardMap = new HashMap<>();
        CreditCardMap.put("credit card", result);
        CreditCardMap.put("bool", UserIsAuthorized);
        return CreditCardMap;
    }

    public static OptionalDouble CheckCardBalance(CreditCard UserCreditCard, boolean UserIsAuthorized){
        double CardBalance = UserCreditCard.getAmountOfMoney();
        if (UserIsAuthorized == true){
            return OptionalDouble.of(CardBalance);
        }
        else{
            System.out.println("Cannot check card balance, card is blocked");
            return OptionalDouble.empty();
        }
    }

    public static OptionalDouble CashoutFromCard(CreditCard UserCreditCard, boolean UserIsAuthorized){
        double CardBalance = UserCreditCard.getAmountOfMoney();
        if (UserIsAuthorized == true){
            return OptionalDouble.of(CardBalance);
        }
        else{
            System.out.println("Cannot cashout from card, card is blocked");
            return OptionalDouble.empty();
        }
    }

    public static OptionalDouble AddMoneyToCard(CreditCard UserCreditCard, boolean UserIsAuthorized){
        double CardBalance = UserCreditCard.getAmountOfMoney();
        if (UserIsAuthorized == true){
            return OptionalDouble.of(CardBalance);
        }
        else{
            System.out.println("Cannot add money to card, card is blocked");
            return OptionalDouble.empty();
        }
    }

    public static void HandleCardBalance(OptionalDouble CardBalance){
        try {
            double balanceOrThrow = CardBalance.orElseThrow(() -> new IllegalStateException("Card is blocked"));
            System.out.println("Card balance: " + balanceOrThrow);
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}