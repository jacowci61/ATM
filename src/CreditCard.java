public class CreditCard extends BankAccount {

    private long CreditCardNumber;
    private int PIN;
    private int AmountOfIncorrectPINsEntered;
    private boolean IsCreditCardBlocked = false;
    //private storing data of blocking

    public CreditCard(BankAccount bankAccount, long CreditCardNumber, int PIN, boolean IsCreditCardBlocked){
        super(bankAccount.getBankAccountID(), bankAccount.getAmountOfMoney());
        this.CreditCardNumber = CreditCardNumber;
        this.PIN = PIN;
        this.IsCreditCardBlocked = IsCreditCardBlocked;
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

    public long getCreditCardNumber(){
        return CreditCardNumber;
    }
    public int getPIN(){
        return PIN;
    }
    public boolean getIsCreditCardBlocked(){
        return IsCreditCardBlocked;
    }
}