import java.math.BigDecimal;

public class Account {

    private final String number;

    private final String customerName;

    private boolean active = true;

    private BigDecimal balance = new BigDecimal(0);

    public Account(String number, String customerName) {
        this.number = number;
        this.customerName = customerName;
        balance = balance.setScale(2);
    }

    public String getNumber() {
        return number;
    }

    public boolean isActive() {
        return active;
    }

    public void close() {
        if (!active) {
            throw new IllegalStateException("Account already closed");
        }
        active = false;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCustomerName() {
        return customerName;
    }

	public void deposit(BigDecimal amount) {
		if (amount == null || lessThanOrEqualToZero(amount)) {
			throw new IllegalArgumentException("You may not deposit negative or zero amounts");
		}
		balance = balance.add(amount.setScale(2, BigDecimal.ROUND_DOWN));
        balance = balance.setScale(2);
	}
	
	private boolean lessThanOrEqualToZero(BigDecimal amount) {
	    int result = BigDecimal.ZERO.compareTo(amount);
	    return result >= 0;
	}
}
