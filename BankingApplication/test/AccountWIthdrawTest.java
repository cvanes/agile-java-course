import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;


public class AccountWIthdrawTest {

    @Test
    public void account_balance_wil_be_correct_after_withdrawal() {
        Account account = new Account("11223344", "mr jones");
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        account.deposit(amount);
        
        BigDecimal withdrawalAmount = new BigDecimal(90).setScale(2);
        account.withdraw(withdrawalAmount);
        
        BigDecimal expectedResult = new BigDecimal(10).setScale(2);
        assertEquals(expectedResult, account.getBalance());
    }
    
    @Test(expected = InsufficientFundsException.class)
    public void withdrawal_throws_InsufficientFundsException_when_amount_exceeds_balance() {
        Account account = new Account("11223344", "mr jones");
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        account.deposit(amount);
        
        BigDecimal withdrawalAmount = new BigDecimal(190).setScale(2);
        account.withdraw(withdrawalAmount);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void withdrawal_throws_IllegalArgumentException_with_zero_amount() {
        Account account = new Account("11223344", "mr jones");
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        account.deposit(amount);
        
        BigDecimal withdrawalAmount = new BigDecimal(0).setScale(2);
        account.withdraw(withdrawalAmount);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void withdrawal_throws_IllegalArgumentException_with_negative_amount() {
        Account account = new Account("11223344", "mr jones");
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        account.deposit(amount);
        
        BigDecimal withdrawalAmount = new BigDecimal(-100).setScale(2);
        account.withdraw(withdrawalAmount);
    }

}
