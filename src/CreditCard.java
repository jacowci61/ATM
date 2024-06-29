public class CreditCard extends BankAccount {

    private long CreditCardNumber;
    private int PIN;
    private int AmountOfIncorrectPINsEntered;
    private boolean IsCreditCardBlocked;
    //private storing data of blocking

    public CreditCard(long CreditCardNumber, int PIN, boolean IsCreditCardBlocked){
        super();
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