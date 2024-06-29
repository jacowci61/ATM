public class CreditCard extends BankAccount {

    private int CreditCardNumber;
    private int PIN;
    private int AmountOfIncorrectPINsEntered;
    private boolean IsCreditCardBlocked;
    //private storing data of blocking


    public CreditCard(int CreditCardNumber, int PIN, boolean IsCreditCardBlocked){
        this.CreditCardNumber = CreditCardNumber;
        this.PIN = PIN;
        this.IsCreditCardBlocked = IsCreditCardBlocked;
    }

    public void setCreditCardNumber(int CreditCardNumber){

    }
    public void setPIN(int PIN){

    }

    public void getCreditCardNumber(){

    }
    public void getPIN(){

    }

}