import static org.junit.Assert.*;

import org.junit.Test;


public class AccountBalanceTest {

	@Test
	public void initial_account_balance_is_zero() {
		Account account = new Account("12345678", "mr jones");
		assertEquals(0, account.getBalance());
	}
	
	@Test
	public void depositing_money_increases_account_balance() {
		Account account = new Account("12345678", "mr jones");
		account.deposit(10);
		assertEquals(10, account.getBalance());
	}
	
	@Test
	public void multiple_deposits_increase_account_balance() {
		Account account = new Account("12345678", "mr jones");
		account.deposit(10);
		assertEquals(10, account.getBalance());
		
		account.deposit(10);
		assertEquals(20, account.getBalance());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void depositing_negative_amount_throws_IllegalArgumentException() {
		Account account = new Account("12345678", "mr jones");
		account.deposit(-10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void depositing_zero_amount_throws_IllegalArgumentException() {
		Account account = new Account("12345678", "mr jones");
		account.deposit(0);
	}

}
