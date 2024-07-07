import java.time.LocalDateTime;

public class CreditCard extends BankAccount {

    private long creditCardID;
    private int PIN;
    private boolean isCreditCardBlocked = false;
    private LocalDateTime localDateTime;

    public CreditCard(BankAccount bankAccount, long creditCardID, int PIN, boolean isCreditCardBlocked, LocalDateTime localDateTime){
        super(bankAccount.getBankAccountID(), bankAccount.getAmountOfMoney());
        this.creditCardID = creditCardID;
        this.PIN = PIN;
        this.isCreditCardBlocked = isCreditCardBlocked;
        this.localDateTime = localDateTime;
    }


    public void setCreditCardID(long creditCardNumber){
        this.creditCardID = creditCardNumber;
    }

    public void setPIN(int PIN){
        this.PIN = PIN;
    }

    public void setIsCreditCardBlocked(Boolean isCreditCardBlocked){
        this.isCreditCardBlocked = isCreditCardBlocked;
    }

    public void setCreditCardBalance(double amount) {
        setAmountOfMoneyOnBankAccount(amount);
    }

    public void setDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }


    public boolean getIsCreditCardBlocked(){
        return isCreditCardBlocked;
    }

    public LocalDateTime getDateOfCreditCardBlock(){
        return localDateTime;
    }

    public long getCreditCardID(){
        return creditCardID;
    }

    public int getCreditCardPIN(){
        return PIN;
    }
}