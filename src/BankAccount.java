public class BankAccount {
    private String bankAccountID;
    private double amountOfMoney;
    // basically make data private (unaccessible from outside the class) and make getters/setters to access it

    public BankAccount(String bankAccountID, double amountOfMoney){
        this.bankAccountID = bankAccountID;
        this.amountOfMoney = amountOfMoney;
    }


    public void setBankAccountID(String bankAccountID){
        this.bankAccountID = bankAccountID;
    }

    public void setAmountOfMoneyOnBankAccount(double newValueOfAmountOfMoneyOnBankAccount){
        this.amountOfMoney = newValueOfAmountOfMoneyOnBankAccount;
    }


    public String getBankAccountID(){
        return bankAccountID;
    }

    public double getAmountOfMoney(){
        return amountOfMoney;
    }
}