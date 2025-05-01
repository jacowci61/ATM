# How to work with program
On startup user will be asked to enter CreditCardID and then provide PIN. After that, user can choose from 3 ATM operations, once operations are complete, program overwrites .txt file with current data and stops working.
# .txt file located in .../ATM/src/ATMData.txt
## Formatting of .txt file
Data in file is split by spaces and represents following elements:
- 1st line is used for reading ATM balance
     - ATMID
     - ATMBalance
- other lines represent credit card information
     - BankAccountID
     - BalanceOfBankAccount
     - CreditCardID
     - IscreditCardBlocked
     - localDateTime when credit card was blocked. If "IscreditCardBlocked == false", 01-01-1970 is used. Otherwise, date represents when user entered 3 incorrect PINs and blocked credit card
## Screenshots
![b5ff42a6-1ee3-4811-8991-4f9da3c7e744 (1)](https://github.com/user-attachments/assets/4077fa07-2ed1-4600-8f00-de77f232daf5)
