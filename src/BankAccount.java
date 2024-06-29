public class BankAccount {
    private String BankAccountID;
    private double AmountOfMoney;
    // basically make data private (unaccessible from outside the class) and make getters/setters to access it

    public BankAccount(String BankAccountID, double AmountOfMoney){
        this.BankAccountID = BankAccountID;
        this.AmountOfMoney = AmountOfMoney;
    }


    public void setBankAccountID(String BankAccountID){
        this.BankAccountID = BankAccountID;
    }

    public String getBankAccountID(){
        return BankAccountID;
    }
    public void setAmountOfMoney(double AmountOfMoney){
        this.AmountOfMoney = AmountOfMoney;
    }

    public double getAmountOfMoney(){
        return AmountOfMoney;
    }
}