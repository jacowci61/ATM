public class CreditCard extends BankAccount {

    private int CreditCardNumber;
    private int PIN;
    private int AmountOfIncorrectPINsEntered;
    private boolean IsCreditCardBlocked;
    //private storing data of blocking

    public CreditCard(int CreditCardNumber, int PIN, boolean IsCreditCardBlocked){
        super();
        this.CreditCardNumber = CreditCardNumber;
        this.PIN = PIN;
        this.IsCreditCardBlocked = IsCreditCardBlocked;
    }

    public void setCreditCardNumber(int CreditCardNumber){
        this.CreditCardNumber = CreditCardNumber;
    }
    public void setPIN(int PIN){
        this.PIN = PIN;
    }

    public int getCreditCardNumber(){
        return CreditCardNumber;
    }
    public int getPIN(){
        return PIN;
    }

}