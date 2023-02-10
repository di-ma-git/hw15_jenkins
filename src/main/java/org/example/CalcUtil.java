package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class CalcUtil {

    public static String divide(int a, int b, int precision) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.divide(bigDecimalB, precision, RoundingMode.HALF_UP).toString();
    }

    public static int generateRandom(int from, int to) {
        Random random = new Random();
        return from + random.nextInt(to - from);
    }
}


