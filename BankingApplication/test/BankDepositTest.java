import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;


public class BankDepositTest {

    @Test
    public void get_balance_returns_correct_balance_after_deposit() {
        String accountNumber = Bank.createAccount("newCustomer").getNumber(); 
        BigDecimal amount = new BigDecimal(10).setScale(2);
        
        Bank.deposit(accountNumber, amount);
        
        BigDecimal balance = Bank.getBalance(accountNumber);
        assertEquals(amount, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_IllegalArgumentException_depositing_using_invalid_account_number() { 
        BigDecimal amount = new BigDecimal(10).setScale(2);
        Bank.deposit("00d", amount);
    }
    
    @Test(expected = AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_depositing_using_no_such_account_number() {
        BigDecimal amount = new BigDecimal(10).setScale(2);
        Bank.deposit("00112233", amount);
    }
    
    @Test(expected = AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_depositing_using_special_account_number() {
        BigDecimal amount = new BigDecimal(10).setScale(2);
        Bank.deposit("00000000", amount);
    }
    
}
