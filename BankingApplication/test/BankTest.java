import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {

	@Test
    public void first_account_created_returns_first_account_number() throws Exception {
        assertEquals(Bank.createAccount("newCustomer"), "00000001");
    }
	
	@Test 
	public void get_balance_returns_account_balance_of_zero_for_new_account() {
		String accountNumber = Bank.createAccount("newCustomer");
		
		String balance = Bank.getBalance(accountNumber);
		assertEquals("£0.00", balance);
	}
	
//    @Test
//    public void second_account_created_returns_second_account_number() throws Exception {
//        Bank.createAccount("newCustomer1");
//        assertEquals(Bank.createAccount("newCustomer2"), "00000002");
//    }

    @Test
    public void account_number_creation_is_sequential() throws Exception {
        int accountNumber1 = Integer.valueOf(Bank.createAccount("newCustomer1"));
        int accountNumber2 = Integer.valueOf(Bank.createAccount("newCustomer2"));
        assertEquals(++accountNumber1, accountNumber2);
    }
}
