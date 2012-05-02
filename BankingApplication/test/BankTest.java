import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {

	@Test
    public void first_account_created_returns_first_account_number() throws Exception {
        assertEquals(Bank.createAccount("newCustomer"), "00000001");
    }

//    @Test
//    public void second_account_created_returns_second_account_number() throws Exception {
//        Bank.createAccount("newCustomer1");
//        assertEquals(Bank.createAccount("newCustomer2"), "00000002");
//    }
}
