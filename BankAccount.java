package AccountProject;

public abstract class BankAccount {


    // private account variables (Personal Info)
    private String last;
    private String first;
    private String street;
    private String city;
    private String state;
    private String zipcode;

    // protected values

    protected int pin;
    protected double balance;
    protected double acctno;


    public BankAccount(String last, String first, double acctno, int pin, String street,
                       String city, String state, String zipcode,
                       double balance) {
        this.last = last;
        this.first = first;
        this.pin = pin;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.balance = balance;
        this.acctno = acctno;
    }

    public void credit(double amt) {            // This method will add to the account balance the amount amt.
        balance += amt;
    }

    public String debit(double amt) {             //This method will decrease the amount balance by amt and return the amount debited.
        if (amt > balance) {
            return "Insufficient Funds";
        }
        else {
            balance -= amt;
            return "Debited: " + amt;
        }
    }


    public double getBalance() {            // Getter to Return Balance
        return balance;
    }           // Getter for check_balance

    public double getAcctno() {             //Getter for the account number
        return acctno;
    }

    @Override
    public String toString() {                                  // toString to put all user data together in one account
        return " BankAccount { " +
                "last= '" + last + '\'' +
                ", first= '" + first + '\'' +
                ", pin = '" + pin + '\'' +
                ", street= '" + street + '\'' +
                ", city= '" + city + '\'' +
                ", state= '" + state + '\'' +
                ", zipcode= '" + zipcode + '\'' +
                ", balance= " + balance +
                ", acctno= '" + acctno + '\'' +
                '}';
    }

    public abstract String kind();          // Returns type of account

    public int getPin() {           // Gets the specific pin being used
        return pin;
    }

    public String getEncryptedDetails() {                           // Calls the PinEncryption Function with the pin from the getter method
        return PinEncryption.E(this.toString(), this.getPin());
    }
}