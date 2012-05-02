import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;


public class BankGetBalanceTest {

    @Test
    public void get_balance_returns_account_balance_of_zero_for_new_account() {
        String accountNumber = Bank.createAccount("newCustomer").getNumber();       
        BigDecimal balance = Bank.getBalance(accountNumber);
        BigDecimal expected = new BigDecimal(0).setScale(2);
        assertEquals(expected, balance);
    }
    
    @Test(expected=AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_getting_balance_for_account_that_does_exist() {
        Bank.getBalance("001");
    }

}
