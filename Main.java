package AccountProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);                   // Initialize the scanner to get user info.
        ArrayList<BankAccount> accounts = new ArrayList<>();        // ArrayList of all accounts in Bank.

        boolean multipleAccts = true;                       // Setting the boolean for adding multiple different accounts
        // it is set as true until told there will only be one account (shown below).

        while (multipleAccts) {
            addAccount(scanner, accounts);           // While multipleAccts is true the loop accesses the addAccount method

            // Checks to see if the user wants to add another account
            System.out.println("Do you want to add another account? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("no")) {
                multipleAccts = false;
            }
        } // while (multipleAccts) loop


        // Creating an infinite loop to now make the interface make the user decide an option
        while (true) {
            System.out.println("Select an Option");
            System.out.println("| Add_Account | Display_Accounts | Check_Balance | Deposit | Withdraw | Exit |");

            String option = scanner.nextLine().trim().toLowerCase();

            if (option.equals("exit")) {                                     // if the option selected is "exit" then program shuts off
                break;
            }

            // Displays options from terminal, after inputting your pin, which decrypts the information,
            // if the pin is incorrect, it displays the incorrect information

            if (option.equals("add_account")) {
                addAccount(scanner, accounts);
            } else if (option.equals("display_accounts")) {
                System.out.println("Enter your PIN: ");

                int enteredPin = scanner.nextInt();
                scanner.nextLine();

                boolean correctPin = false;

                for (BankAccount account : accounts) {                  // If the pin was correct to the one that was input for your account
                    if (enteredPin == account.getPin()) {               // then it will then let the accounts be displayed without being encrypted.
                        correctPin = true;
                        break;
                    }
                }

                if (correctPin) {
                    displayAccounts(accounts);
                } else {
                    System.out.println("Incorrect PIN. Displaying encrypted account details:");

                    for (BankAccount account : accounts) {
                        System.out.println(account.getEncryptedDetails());
                    }
                }

            } else {
                System.out.println("Enter account number: ");
                double accountnumber = scanner.nextDouble();
                scanner.nextLine();


                BankAccount foundAccount = searchAccountByNumber(accounts, accountnumber);
                if (foundAccount == null) {                                                 // if the FoundAccount doesnt exist then the loop continues from the top
                    System.out.println("Account not found.");
                    continue;
                }

                System.out.println("Enter your PIN: ");
                int enteredPin = scanner.nextInt();
                scanner.nextLine();

                if (enteredPin != foundAccount.getPin()) {
                    System.out.println("Incorrect PIN. Displaying encrypted account details:");
                    System.out.println(foundAccount.getEncryptedDetails());
                    continue;
                }

                if (option.equals("deposit")) {
                    System.out.println("Enter Amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    foundAccount.credit(amount);                                // Deposits amount into the found account
                    System.out.println("Deposited: " + amount);

                } else if (option.equals("withdraw")) {
                    System.out.println("Enter Amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    foundAccount.debit(amount);
                    System.out.println("Withdrawn: " + amount);

                } else if (option.equals("check_balance")) {
                    System.out.println("Current balance: " + foundAccount.getBalance());
                } else {
                    System.out.println("Invalid operation.");
                    continue;
                }
                // Display updated account details
                System.out.println("Updated account details:");
                System.out.println(foundAccount);

            }
        }
        scanner.close();

    }// psvm main


    private static void addAccount(Scanner scanner, ArrayList<BankAccount> accounts) {
        System.out.println("-----Bank Account System-----");
        System.out.println("Input Personal and Bank Account info Below");

        // Choice of Account Type
        System.out.println("Enter account type (checking/savings):");
        String accountType = scanner.nextLine().trim().toLowerCase();       // Trims and sets the response word as lowercase to make it
        // uniform and acceptable for sorting accounts for creation
        // Other Personal info
        System.out.println("Enter last name:");
        String last = scanner.nextLine();

        System.out.println("Enter first name:");
        String first = scanner.nextLine();

        System.out.println("Enter account number:");
        double acctno = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter a 4 digit PIN number: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter street address:");
        String street = scanner.nextLine();

        System.out.println("Enter city:");
        String city = scanner.nextLine();

        System.out.println("Enter state:");
        String state = scanner.nextLine();

        System.out.println("Enter zipcode:");
        String zipcode = scanner.nextLine();

        System.out.println("Enter balance:");
        double balance = scanner.nextDouble();

        scanner.nextLine();                         // Consumes the newline character in order
                                                    // to not read residual input from other line

        // Based on the user input, the account can then be created:
        if (accountType.equals("checking")) {

            System.out.println("Can this account be overdrawn? (true/false): ");
            boolean canOverdraw = false;                                        // Makes the boolean canOverdraw a default value

            // Creating a loop to catch an error if true/false is put in incorrectly

            while(true) {
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("true")) {
                    canOverdraw = true;
                    break;
                }
                else if (input.equals("False")) {
                    canOverdraw = false;
                    break;
                }
                else {
                    System.out.println("Invalid input. Please enter 'true' or 'false': ");
                }
            }

            Checking checkingAccount = new Checking(last, first, acctno, pin, street, city,
                    state, zipcode, balance, canOverdraw);

            accounts.add(checkingAccount);                              // adds the checking account to the ArrayList
        } else if (accountType.equals("savings")) {

            Savings savingsAccount = new Savings(last, first, acctno, pin, street, city, state,
                    zipcode, balance);

            accounts.add(savingsAccount);
        } else {
            System.out.println("Invalid account type entered.");
        }

    }


    // This function finds the account by utilizing the account number
    private static BankAccount searchAccountByNumber(ArrayList<BankAccount> accounts, double accountnumber) {

        for (BankAccount account : accounts) {
            if (account.getAcctno() == accountnumber) {
                return account;
            }
        }
        return null;
    }

    public static void displayAccounts(ArrayList<BankAccount> accounts) {

        // Shows all of the account details
        System.out.println("Accounts Display");
        System.out.println("Total number of accounts: " + accounts.size());

        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

}