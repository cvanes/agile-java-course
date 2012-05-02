import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static Map<String, Account> accounts = new HashMap<String, Account>();

    private static int lastAccountNumber = 0;

    public static String createAccount(String name) {
        String accountNumber = nextAccountNumber();
        accounts.put(accountNumber, new Account(accountNumber, name));
        return accountNumber;
    }

	public static String getBalance(String accountNumber) {
		long balance  = accounts.get(accountNumber).getBalance();
		if (balance == 0) {
			return "£0.00";
		} else {
			String s = "£" + String.valueOf(balance);
			String balanceString = s.substring(0, s.length() -3) + "." + s.substring(s.length() - 2);
			return balanceString;
		}
	}

	private static String nextAccountNumber() {
        return String.format("%08d", ++lastAccountNumber);
    }
}
