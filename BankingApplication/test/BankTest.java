import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BankTest {

	@Test
    public void first_account_created_returns_first_account_number() throws Exception {
        assertEquals(Bank.createAccount("newCustomer").getNumber(), "00000001");
    }

    @Test
    public void creating_account_marks_account_as_active() throws Exception {
        Account account = Bank.createAccount("newCustomer");
        assertTrue(account.isActive());
    }

	@Test
	public void get_balance_returns_account_balance_of_zero_for_new_account() {
		String accountNumber = Bank.createAccount("newCustomer").getNumber();

		String balance = Bank.getBalance(accountNumber);
		assertEquals("�0.00", balance);
	}

    @Test(expected=IllegalArgumentException.class)
    public void account_creation_with_null_name_fails() throws Exception {
        Bank.createAccount(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void account_creation_with_empty_name_fails() throws Exception {
        Bank.createAccount("");
    }

    @Test
    public void account_number_creation_is_sequential() throws Exception {
        int accountNumber1 = Integer.valueOf(Bank.createAccount("newCustomer1").getNumber());
        int accountNumber2 = Integer.valueOf(Bank.createAccount("newCustomer2").getNumber());
        assertEquals(++accountNumber1, accountNumber2);
    }

    @Test
    public void closing_account_marks_account_as_inactive() throws Exception {
        Account account = Bank.createAccount("newCustomer");
        Bank.closeAccount(account.getNumber());
        assertFalse(account.isActive());
    }
}
