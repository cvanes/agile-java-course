import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountBalanceTest {

    @Test
    public void initial_account_balance_is_zero() {
        Account account = new Account("12345678", "mr jones");
        assertEquals(BigDecimal.ZERO.setScale(2), account.getBalance());
    }

    @Test
    public void depositing_money_increases_account_balance() {
        Account account = new Account("12345678", "mr jones");
        account.deposit(new BigDecimal(10));
        assertEquals(new BigDecimal(10).setScale(2), account.getBalance());
    }

    @Test
    public void multiple_deposits_increase_account_balance() {
        Account account = new Account("12345678", "mr jones");
        account.deposit(new BigDecimal(10));
        assertEquals(new BigDecimal(10).setScale(2), account.getBalance());

        account.deposit(new BigDecimal(10));
        assertEquals(new BigDecimal(20).setScale(2), account.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositing_negative_amount_throws_IllegalArgumentException() {
        Account account = new Account("12345678", "mr jones");
        account.deposit(new BigDecimal(-10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositing_zero_amount_throws_IllegalArgumentException() {
        Account account = new Account("12345678", "mr jones");
        account.deposit(new BigDecimal(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositing_null_amount_throws_IllegalArgumentException() {
        Account account = new Account("12345678", "mr jones");
        account.deposit(null);
    }
    
    @Test
    public void depositing_amount_with_more_than_two_decimal_places_truncates() {
        Account account = new Account("12345678", "mr jones");
        BigDecimal amount = new BigDecimal(10.034);
        account.deposit(amount);
        
        BigDecimal balance = account.getBalance();
        System.out.println(account.getBalance());
        BigDecimal expected = new BigDecimal(10.03).setScale(2, BigDecimal.ROUND_UP);
        assertEquals(expected, balance);
    }
}
