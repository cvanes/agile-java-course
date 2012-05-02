import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankQueryTest {

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void return_list_with_single_account() throws Exception {
        bank.createAccount("customer");
        List<Account> accounts = bank.getAllAccounts("customer");
        assertEquals(accounts.size(), 1);
    }

    @Test
    public void return_list_with_multiple_accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        bank.createAccount("customer");
        bank.createAccount("customer3");
        List<Account> accounts = bank.getAllAccounts("customer");
        assertEquals(accounts.size(), 2);
    }

    @Test
    public void return_empty_list_for_customer_with_no_Accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        bank.createAccount("customer");
        bank.createAccount("customer3");
        List<Account> accounts = bank.getAllAccounts("customer5");
        assertEquals(accounts.size(), 0);
    }
}
