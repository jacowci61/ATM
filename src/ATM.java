import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    public double CashAvailableInATM;

    public static Map<CreditCard,Boolean> Authorization(long CreditCardNumber, String filePath){

        boolean UserIsAuthorized;

        String query = String.valueOf(CreditCardNumber);
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
                break;
            }
        }
        //int index = CreditCardsList.indexOf(result);
        reader.close();

        Map<CreditCard,Boolean> CreditCardMap = new HashMap<>();
        CreditCardMap.put(result, UserIsAuthorized);
        return CreditCardMap;
    }

    public void ChangeStateOfCreditCard(){
    // used for blocking/unblocking access to credit card
    }

    public void CheckCardBalance(){

    }

    public void CashoutFromCard(){

    }

    public void AddMoneyToCard(){

    }
}