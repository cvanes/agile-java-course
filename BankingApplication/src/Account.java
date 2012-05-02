public class Account {

    private final String number;

    private final String customerName;

    private boolean active = true;

    private long balance = 0L;

    public Account(String number, String customerName) {
        this.number = number;
        this.customerName = customerName;
    }

    public String getNumber() {
        return number;
    }

    public boolean isActive() {
        return active;
    }

    public long getBalance() {
        return balance;
    }

    public String getCustomerName() {
        return customerName;
    }

	public void deposit(long amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("You may not deposit negative or zero amounts");
		}
		balance += amount;
	}
}
