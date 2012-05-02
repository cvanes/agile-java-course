import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class BankGetBalanceTest {

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void get_balance_returns_account_balance_of_zero_for_new_account() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        BigDecimal balance = bank.getBalance(accountNumber);
        BigDecimal expected = new BigDecimal(0).setScale(2);
        assertEquals(expected, balance);
    }

    @Test
    public void get_balance_returns_correct_balance_after_deposit() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        BigDecimal amount = new BigDecimal(10).setScale(2);

        bank.deposit(accountNumber, amount);

        BigDecimal balance = bank.getBalance(accountNumber);
        assertEquals(amount, balance);
    }

    @Test(expected=AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_getting_balance_for_account_that_does_exist() {
        bank.getBalance("00100000");
    }

}
