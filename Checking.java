package AccountProject;

public class Checking extends BankAccount {

    private boolean canOverdraw;    // allows overdraft capability

    public Checking(String last, String first, double acctno, int pin, String street,
                    String city, String state, String zipcode,
                    double balance, boolean canOverdraw) {
        // Call to superclass constructor to initialize inherited fields
        super(last, first, acctno, pin, street, city, state, zipcode, balance);
        this.canOverdraw = canOverdraw; // Initialize subclass-specific field
    }
    @Override
    public String debit(double amt) {

        if (amt > balance && !canOverdraw) {                   // If the amount taken out is bigger than balance and you cannot overdraw
            return "Insufficient funds, you cannot overdraw";
        }
        else {
            balance -= amt;
            return "Debited: " + amt;
        }
    }

    public String kind() {
        return "checking";
    }

    @Override
    public String toString() {         // Uses the super to grab the toString from the BankAccount then adds the can overdraw boolean
        return "Checking{ " +
                super.toString() +
                "canOverdraw=" + canOverdraw +
                '}';
    }
}



