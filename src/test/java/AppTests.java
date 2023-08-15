import org.example.config.AppConfig;
import org.example.handlers.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTests {

    private UserHandler userHandler;

    @BeforeEach
    public void setUp() {
        userHandler = new UserHandler();
    }


//    This test aims to verify if the reimbursement amount calculations for a given claim are accurate within the Claim class.
//     We compare the expected reimbursement amount with the calculated reimbursement from the getReimbursementAmount() method of the Claim class.
    @Test
    public void testReimbursementCalculation() {
        double days = 5;
        double distance = 150;
        double expectedReimbursement = (days * 15) + (distance * 0.3);

        double calculatedReimbursement = days * AppConfig.getDailyAllowanceRate() + distance * AppConfig.getMileageRate();

        assertEquals(expectedReimbursement, calculatedReimbursement);
    }

//    This test is designed to verify if the parseFormData method in the UserHandler class correctly processes form data.
//    We check if the values in the returned map match the expected values based on the input form data.
    @Test
    public void testFormDataParsing() throws Exception {
        UserHandler userHandler = new UserHandler();
        String sampleFormData = "tripDate=2023-08-11&receiptType=Hotel&days=2&distance=100";
        Map<String, String> parsedData = userHandler.parseFormData(sampleFormData);

        assertEquals("2023-08-11", parsedData.get("tripDate"));
        assertEquals("Hotel", parsedData.get("receiptType"));
        assertEquals("2", parsedData.get("days"));
        assertEquals("100", parsedData.get("distance"));
    }

}