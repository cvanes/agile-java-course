import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class BankWithdrawalTest {

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }
    
    @Test
    public void account_balance_wil_be_correct_after_withdrawal() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        bank.deposit(accountNumber, amount);
        
        BigDecimal withdrawalAmount = new BigDecimal(90).setScale(2);
        bank.withdraw(accountNumber, withdrawalAmount);
        
        BigDecimal expectedResult = new BigDecimal(10).setScale(2);
        assertEquals(expectedResult, bank.getBalance(accountNumber));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void throws_IllegalArgumentException_for_invalid_account_number() {
        BigDecimal withdrawalAmount = new BigDecimal(90).setScale(2);
        bank.withdraw("@@@@@@", withdrawalAmount);
    }
    
    @Test(expected = AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_for_no_such_account_number() {
        BigDecimal withdrawalAmount = new BigDecimal(90).setScale(2);
        bank.withdraw("88888888", withdrawalAmount);
    }
    
    @Test(expected = InsufficientFundsException.class)
    public void withdrawal_throws_InsufficientFundsException_when_amount_exceeds_balance() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        bank.deposit(accountNumber, amount);
     
        BigDecimal withdrawalAmount = new BigDecimal(1000).setScale(2);
        bank.withdraw(accountNumber, withdrawalAmount);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void withdrawal_throws_IllegalArgumentException_with_zero_amount() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        bank.deposit(accountNumber, amount);
     
        BigDecimal withdrawalAmount = new BigDecimal(0).setScale(2);
        bank.withdraw(accountNumber, withdrawalAmount);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void withdrawal_throws_IllegalArgumentException_with_negative_amount() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        
        BigDecimal amount = new BigDecimal(100).setScale(2);
        bank.deposit(accountNumber, amount);
     
        BigDecimal withdrawalAmount = new BigDecimal(-100).setScale(2);
        bank.withdraw(accountNumber, withdrawalAmount);
    }

}
