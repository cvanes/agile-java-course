import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static Map<String, Account> accounts = new HashMap<String, Account>();

    private static int lastAccountNumber = 0;

    private static int maxAccountNumber = 999999999;

    public static Account createAccount(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String accountNumber = nextAccountNumber();
        Account newAccount = new Account(accountNumber, name);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }

	public static String getBalance(String accountNumber) {
		long balance  = accounts.get(accountNumber).getBalance();
		if (balance == 0) {
			return "�0.00";
		} else {
			String s = "�" + String.valueOf(balance);
			String balanceString = s.substring(0, s.length() -3) + "." + s.substring(s.length() - 2);
			return balanceString;
		}
	}

    private static String nextAccountNumber() {
        if (lastAccountNumber == maxAccountNumber)
            throw new RuntimeException("Maximum account limit reached");
        return String.format("%08d", ++lastAccountNumber);
    }

    public static void closeAccount(String accountNumber) {
        validateAccountNumber(accountNumber);
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("No such account");
        }
        account.close();
    }

    private static void validateAccountNumber(String accountNumber) {
        boolean valid = accountNumber != null &&
                accountNumber.matches("[0-9]{8}");

        if (!valid) {
            throw new IllegalArgumentException("Invalid account number");
        }
    }
}
