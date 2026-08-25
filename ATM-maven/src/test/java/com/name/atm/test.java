import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkWithDataTest {

    @Test
    void readStringArrayIntoObjectArray_returnsCorrectBalance() {
        String testFilePath = "src/test/resources/test-accounts.txt";

        List<CreditCard> creditCards = WorkWithData.readStringArrayIntoObjectArray(testFilePath);
        double actualBalance = creditCards.get(1).getAmountOfMoney();
        int actualPIN = creditCards.get(1).getCreditCardPIN();
        long actualCardNumber = creditCards.get(1).getCreditCardID();
        String actualBankAccountID = creditCards.get(1).getBankAccountID();
        boolean actualBLockState = creditCards.get(1).getIsCreditCardBlocked();

        assertEquals(46540.0, actualBalance);
        assertEquals(2048, actualPIN);
        assertEquals(4789566628948954l, actualCardNumber);
        assertEquals("u345", actualBankAccountID);
        assertEquals(true, actualBLockState);
    }
}