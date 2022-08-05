package examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

// The Test class should start or end with "Test"
public class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    public static void setUp()  {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    public void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    @DisplayName("Simple addition should work")
    @RepeatedTest(2)
    public void testAddition() {
        assertEquals(10, calculator.add(4, 5));
    }
}
