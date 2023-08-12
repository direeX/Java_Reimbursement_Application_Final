import org.example.models.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    private Administrator admin;

    @BeforeEach
    public void setup() {
        admin = new Administrator();
    }

    @Test
    public void testSetValidDailyAllowanceRate() {
        BigDecimal rate = new BigDecimal("20");
        admin.setDailyAllowanceRate(rate);
        // Sprawdz czy wartość została ustawiona (tutaj można by dodać getter do klasy Administrator)
    }


    @Test
    public void testSetValidMileageRate() {
        BigDecimal rate = new BigDecimal("0.5");
        admin.setMileageRate(rate);
        // Sprawdz czy wartość została ustawiona
    }



    // Można dodać podobne testy dla ustawiania limitów paragonów.
}