import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private final Map<String, Account> accounts = new HashMap<String, Account>();

    private int lastAccountNumber = 0;

    private final int maxAccountNumber = 999999999;

    public Account createAccount(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String accountNumber = nextAccountNumber();
        Account newAccount = new Account(accountNumber, name);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }

	public BigDecimal getBalance(String accountNumber) {
	    Account account = accounts.get(accountNumber);
	    if (account == null) {
	        throw new AccountDoesNotExistException();
	    }

	    return account.getBalance();
	}

    private String nextAccountNumber() {
        if (lastAccountNumber == maxAccountNumber)
            throw new RuntimeException("Maximum account limit reached");
        return String.format("%08d", ++lastAccountNumber);
    }

    public void closeAccount(String accountNumber) {
        validateAccountNumber(accountNumber);
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        account.close();
    }

    private void validateAccountNumber(String accountNumber) {
        boolean valid = accountNumber != null &&
                accountNumber.matches("[0-9]{8}");

        if (!valid) {
            throw new IllegalArgumentException("Invalid account number");
        }
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        validateAccountNumber(accountNumber);
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        account.deposit(amount);
    }

    public List<Account> getAllAccounts(String customerName) {
        List<Account> accountList = new ArrayList<Account>();
        for (Account account : accounts.values()) {
            if (account.getCustomerName().equals(customerName)) {
                accountList.add(account);
            }
        }
        return accountList;
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        validateAccountNumber(accountNumber);
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        account.withdraw(amount);        
    }
}
