package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtilService {

    private static final String ONLY_NUMBER_REGEX = "(\\d+)";

    public static void main(String[] args) {
        System.out.println(cutAllDigits("1213dfdf;1212"));
    }

    public static boolean hasOnlyDigits(String value) {
        if (value == null) {
            return false;
        }
        return value.matches(ONLY_NUMBER_REGEX);
    }

    public static boolean hasSomeDigits(String value) {
        if (value == null) {
            return false;
        }
        Pattern someNumberPattern = Pattern.compile("\\d");
        Matcher m = someNumberPattern.matcher(value);
        return m.find();
    }

    public static List<String> cutAllDigits(String stringWithDigits) {
        if (stringWithDigits == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(stringWithDigits);
        List<String> extractedDigits = new ArrayList<>();
        while (matcher.find()) {
            extractedDigits.add(matcher.group());
        }
        return extractedDigits;
    }

}
