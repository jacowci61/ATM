import java.time.LocalDateTime;

public class CreditCard extends BankAccount {

    private long CreditCardNumber;
    private int PIN;
    private int AmountOfIncorrectPINsEntered;
    private boolean IsCreditCardBlocked = false;
    private LocalDateTime localDateTime;
    //private storing data of blocking

    public CreditCard(BankAccount bankAccount, long CreditCardNumber, int PIN, boolean IsCreditCardBlocked, LocalDateTime localDateTime){
        super(bankAccount.getBankAccountID(), bankAccount.getAmountOfMoney());
        this.CreditCardNumber = CreditCardNumber;
        this.PIN = PIN;
        this.IsCreditCardBlocked = IsCreditCardBlocked;
        this.localDateTime = localDateTime;
    }

    public void setCreditCardNumber(long CreditCardNumber){
        this.CreditCardNumber = CreditCardNumber;
    }
    public void setPIN(int PIN){
        this.PIN = PIN;
    }
    public void setIsCreditCardBlocked(Boolean IsCreditCardBlocked){
        this.IsCreditCardBlocked = IsCreditCardBlocked;
    }
    public void changeBalance(double amount) {
        setAmountOfMoney(amount);
    }

    public long getCreditCardNumber(){
        return CreditCardNumber;
    }
    public int getPIN(){
        return PIN;
    }
    public boolean getIsCreditCardBlocked(){
        return IsCreditCardBlocked;
    }
    public void setDate(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getDate(){
        return localDateTime;
    }
}