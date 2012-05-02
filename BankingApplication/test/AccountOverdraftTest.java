import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;


public class AccountOverdraftTest {

    @Test
    public void new_account_will_have_overdraft_of_zero() {
        Account account = new Account("99887766", "mr jones");
        assertEquals(BigDecimal.ZERO, account.getOverdraftLimit());
    }
    
    @Test
    public void overdraft_limit_is_set_successfully() {
        Account account = new Account("99887766", "mr jones");
        BigDecimal limit = new BigDecimal(100).setScale(2);
        account.setOverdraftLimit(limit);
        
        assertEquals(limit, account.getOverdraftLimit());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void overdraft_limit_cannot_be_set_to_negative_number() {
        Account account = new Account("99887766", "mr jones");
        BigDecimal limit = new BigDecimal(-100).setScale(2);
        account.setOverdraftLimit(limit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void overdraft_limit_cannot_be_set_to_null() {
        Account account = new Account("99887766", "mr jones");
        account.setOverdraftLimit(null);
    }
    
    @Test
    public void withdrawal_exceeding_balance_but_not_overdraft_is_successful() {
        Account account = new Account("99887766", "mr jones");
        BigDecimal limit = new BigDecimal(100).setScale(2);
        account.setOverdraftLimit(limit);
        
        BigDecimal withdrawalAmount = new BigDecimal(60).setScale(2);
        account.withdraw(withdrawalAmount);
        
        BigDecimal expected = new BigDecimal(-60).setScale(2);
        assertEquals(expected, account.getBalance());
    }
    
    @Test(expected =IllegalStateException.class)
    public void throws_IllegalStateException_when_closing_overdrawn_account() {
        Account account = new Account("99887766", "mr jones");
        BigDecimal limit = new BigDecimal(100).setScale(2);
        account.setOverdraftLimit(limit);
        
        BigDecimal withdrawalAmount = new BigDecimal(50).setScale(2);
        account.withdraw(withdrawalAmount);
        account.close();
    }
    
 

}
