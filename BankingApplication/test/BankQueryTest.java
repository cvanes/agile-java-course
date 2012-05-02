import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;
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
        List<Account> accounts = bank.getCustomerAccounts("customer");
        assertEquals(accounts.size(), 1);
    }

    @Test
    public void return_list_with_multiple_accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        bank.createAccount("customer");
        bank.createAccount("customer3");
        List<Account> accounts = bank.getCustomerAccounts("customer");
        assertEquals(accounts.size(), 2);
    }

    @Test
    public void return_empty_list_for_customer_with_no_Accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        bank.createAccount("customer");
        bank.createAccount("customer3");
        List<Account> accounts = bank.getCustomerAccounts("customer5");
        assertEquals(accounts.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void return_empty_list_for_null_customer_with_no_Accounts() throws Exception {

        List<Account> accounts = bank.getCustomerAccounts(null);
        assertEquals(accounts.size(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void return_empty_list_for_blank_name_customer_with_no_Accounts() throws Exception {

        List<Account> accounts = bank.getCustomerAccounts("");
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void returns_list_of_all_accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        bank.createAccount("customer");
        bank.createAccount("customer3");
        Collection<Account> accounts = bank.getAllAccounts();
        assertEquals(accounts.size(), 4);
    }

    @Test
    public void returns_empty_list_when_no_accounts() throws Exception {
        Collection<Account> accounts = bank.getAllAccounts();
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void returns_none_for_number_of_accounts_when_no_accounts() throws Exception {
        int  numberofaccounts= bank.getNumberOfAccounts();
        assertEquals(numberofaccounts, 0);
    }

    @Test
    public void returns_number_of_accountsd() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        Account acc = bank.createAccount("customer");
        bank.createAccount("customer3");
        bank.closeAccount(acc.getNumber());
        int accountsNumber = bank.getNumberOfAccounts();
        assertEquals(accountsNumber, 4);
    }

    @Test
    public void returns_list_of_all_accounts_both_open_And_closed() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        Account acc = bank.createAccount("customer");
        bank.createAccount("customer3");
        bank.closeAccount(acc.getNumber());
        Collection<Account> accounts = bank.getAllAccounts();
        assertEquals(accounts.size(), 4);
    }

    @Test
    public void returns_list_of_all_open_accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        Account acc = bank.createAccount("customer");
        bank.createAccount("customer3");
        bank.closeAccount(acc.getNumber());
        List<Account> accounts = bank.getOpenAccounts();
        assertEquals(accounts.size(), 3);
    }

    @Test
    public void returns_list_of_all_closed_accounts() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer2");
        Account acc = bank.createAccount("customer");
        bank.createAccount("customer3");
        bank.closeAccount(acc.getNumber());
        List<Account> accounts = bank.getClosedAccounts();
        assertEquals(accounts.size(), 1);
    }

    @Test
    public void returns_single_account_for_customer() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer");
        Account newAccount = bank.createAccount("customer");
        Account account = bank.getAccount(newAccount.getNumber());
        assertEquals(account.getNumber(), newAccount.getNumber());
        assertEquals(account.getCustomerName(), "customer");
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void throws_exception_when_getting_null_existent_account() throws Exception {
        bank.getAccount(null);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void throws_exception_when_getting_non_existent_account() throws Exception {
        bank.getAccount("123");
    }

    @Test
    public void returns_none_if_no_accounts_overdrawn() throws Exception {
        bank.createAccount("customer");
        bank.createAccount("customer");

        assertEquals(0, bank.getOverdrawnAccounts().size());
    }

    @Test
    public void returns_number_of_accounts_overdrawn() throws Exception {
        bank.createAccount("customer");
        Account acc2 =  bank.createAccount("customer");
        bank.setOverdraftLimit(acc2.getNumber(), new BigDecimal(100));
        bank.withdraw(acc2.getNumber(), new BigDecimal(1));
        Account acc3 = bank.createAccount("customer");
        bank.setOverdraftLimit(acc3.getNumber(), new BigDecimal(100));
        bank.withdraw(acc3.getNumber(), new BigDecimal(1));

        assertEquals(2, bank.getOverdrawnAccounts().size());
    }


}
