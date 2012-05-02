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

    private static String nextAccountNumber() {
        return String.format("%08d", ++lastAccountNumber);
    }

}
