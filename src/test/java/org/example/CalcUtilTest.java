package org.example;

import org.junit.jupiter.api.Test;

import static org.example.CalcUtil.divide;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalcUtilTest extends DefaultTest {

    @Test
    public void precisionTest() {
        // 1/3 with 1000 precision => 0.33333...3
        String oneDivideThree = CalcUtil.divide(1, 3, 1000);
        assertTrue(oneDivideThree.length() >= 1000);
        assertEquals('3', oneDivideThree.toCharArray()[1000]);
    }

    @Test
    public void randomNumbersTest() {
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum = sum + CalcUtil.generateRandom(0, 1000);
        }
        float deviation = ((sum - 1_000_000 * 500) / 1_000_000f) * 100 / 500f;
        assertTrue(deviation < 1);
    }


}
