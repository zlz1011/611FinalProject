bank ATM

Team member:

Lingzhuo Zhao   U85221615  lucienz@bu.edu,
Hao He  	U63649967  jermae@bu.edu
Chuyang Zhou	U23879399  cyzhou@bu.edu

Compilation and execution instructions:
Our projects has 26 class files, 2 interface files and one folder named "Data".
You need to create an empty folder named "Data" first!!!Or you could not run the program.

javac main.java
java main
(If use these instructions in the Console, you should put/create "Data" folder with java files. But if you want to run these codes in IntelliJ IDEA/Eclipse, you should put "Data" folder in the same place of "src")

If run with "Data" folder empty, some txt files will be created automatically:
yyyy-MM-dd_report.txt: daily transaction report
bank_money.txt: money of bank
date.txt: contains the date
exchange_rate.txt: default exchange rate
info.txt: information of customers, which is initially empty
manager_list.txt: the list of managers, by default there is a manager with managerID: admin and password: 123456
namePass.txt: the customerID and password

AccountFrame class: The AccountFrame class is used to check and see saving or checking account of the user. The user could withdraw, deposit and transfer money from saving account to checking account through this frame. It implements the ReadData interface.

BalanceFrame class:The BalanceFrame is used to check and see user's current balances of saving and checking account. It also shows the loan if the user has.

BankSystem class:BankSystem is the main class of this project. It is used to run the program. It initializes the necessary files needed for the bank system.

CheckInput interface:The CheckInput interface is only used to check if the user entered is integer. 

CloseAccountFrame class:The CloseAccountFrame is used to let user close his/her account. 
User cannot close his/her account if he/she has loan. 

DataModify class:The DataModify class is used to modify the data in text files if needed. 
It holds the static methods for modifying or deleting specific data in files.

EnrolledFrame class:EnrolledFrame is used when the user enrolled his/her account. It will show 8 buttons with different function.

FindTimeDiffer class:FindTimeDiffer class is used to find the difference in days between two dates.

GetData class:It is used for reading all text from txt files.

GetDate class: Get current date from date.txt and save the changed date to it.

LoanFrame class:The LoanFrame is used when the user request a loan in the enrolled frame.
It sets the loan as needed.

ManagerBankMoneyFrame class:Create a frame which manager can get and change the money of the bank of every currency.

ManagerCheckCustomerFrame class:Create a frame which manager can check all customers with brief information or check a specific customer by the id and get all transaction recording of the customer.

ManagerDailyReportFrame class: Create a frame which manager can get a report of today or a specific day.

ManagerEditMemberFrame class:Create a frame which manager can check the list of managers and add or delete a manager, however a manager can not delete himself/herself.

ManagerExchangeRateFrame class: Create a frame which manager can modify the exchange rate of a currency to USD.

ManagerLoginFrame: Create a frame allows manager to login.

ManagerSelectOperationFrame: Once manager login, create a frame contains buttons for manager to chose an operation from.

PayLoanFrame:PayLoanFrame is used when the user pay his/her loan. It asks the user to enter the payment.

ReadData interface:ReadData interface is used to read some data from the specific file if needed.

SignOnFrame class:SignOnFrame is used when a user opens a new account. It asks the basic info of the user.

TransactionFrame class:The TransactionFrame is used to show all recent 15 transactions of a user did.

TransferCheckingFrame class:TransferCheckingFrame is used when user want to transfer money from saving account to checking account.

TransferFrame class:TransferFrame is used when user want to transfer money from his/her account to other user's account.

WelcomeFrame class:WelcomeFrame is the main frame and used when start the program.It checks the entered username/password.

WithdrawDepositFrame class:It is used when a user wants to withdraw or deposit money.

WriteData class:WriteData is used to write information to the specific file.

