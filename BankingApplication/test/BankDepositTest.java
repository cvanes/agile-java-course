import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class BankDepositTest {

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void get_balance_returns_correct_balance_after_deposit() {
        String accountNumber = bank.createAccount("newCustomer").getNumber();
        BigDecimal amount = new BigDecimal(10).setScale(2);

        bank.deposit(accountNumber, amount);

        BigDecimal balance = bank.getBalance(accountNumber);
        assertEquals(amount, balance);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_IllegalArgumentException_depositing_using_invalid_account_number() {
        BigDecimal amount = new BigDecimal(10).setScale(2);
        bank.deposit("00d", amount);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_depositing_using_no_such_account_number() {
        BigDecimal amount = new BigDecimal(10).setScale(2);
        bank.deposit("00112233", amount);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void throws_AccountDoesNotExistException_depositing_using_special_account_number() {
        BigDecimal amount = new BigDecimal(10).setScale(2);
        bank.deposit("00000000", amount);
    }

}
