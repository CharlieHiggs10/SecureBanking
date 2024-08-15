package AccountProject;

public class Savings extends BankAccount{

    private int withdrawCount; // This is going to track withdrawls

    public Savings(String last, String first, double acctno, int pin,  String street,
                   String city, String state, String zipcode,
                   double balance) {
        // Call to superclass constructor to initialize inherited fields
        super(last, first, acctno, pin, street, city, state, zipcode, balance);
        this.withdrawCount = 0;
    }

    public String debit(double amt) {               // Sets the boundaries for taking out money
        if (amt > balance) {
            return "Insufficient Funds";
        }
        else {
            balance -= amt;
            withdrawCount++;
            return "Debited: " + amt;
        }
    }

    public String kind() {
        return "savings";
    }

    @Override
    public String toString() {
        return "Savings {" +
                super.toString() +
                ", withdrawCount=" + withdrawCount +
                '}';
    }
}

