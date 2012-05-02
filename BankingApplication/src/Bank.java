import java.util.HashMap;
import java.util.Map;

public class Bank {

    private static Map<String, Account> accounts = new HashMap<String, Account>();

    public static String createAccount(String name) {
        String accountNumber = "00000001";
        accounts.put(accountNumber, new Account(accountNumber, name));
        return accountNumber;
    }

}
