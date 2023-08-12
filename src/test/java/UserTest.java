import org.example.models.ReceiptType;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testClaimDailyAllowance() {
        User user = new User();
        double result = user.claimDailyAllowance(5);
        assertEquals(75, result);  // 5 days * 15$/day
    }

    @Test
    public void testAddReceipt() {
        User user = new User();
        boolean isAdded = user.addReceipt(ReceiptType.HOTEL, new BigDecimal("200"));
        assertTrue(isAdded);
    }

    @Test
    public void testInvalidReceiptAmount() {
        User user = new User();
        boolean isAdded = user.addReceipt(ReceiptType.HOTEL, new BigDecimal("-200"));
        assertFalse(isAdded);
    }

    // Można dodać więcej testów w podobnym stylu...
}