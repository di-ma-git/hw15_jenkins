package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegexUtilServiceUnitTest extends DefaultTest{

    @Test
    public void hasOnlyDigitsTest(){
        assertTrue(RegexUtilService.hasOnlyDigits("311"));
        assertFalse(RegexUtilService.hasOnlyDigits("3dfa11"));
        assertFalse(RegexUtilService.hasOnlyDigits(""));
        assertFalse(RegexUtilService.hasOnlyDigits(null));
    }

    @Test
    public void hasSomeDigitsTest(){
        assertTrue(RegexUtilService.hasSomeDigits("311"));
        assertTrue(RegexUtilService.hasSomeDigits("3dfa11"));
        assertFalse(RegexUtilService.hasSomeDigits(""));
        assertFalse(RegexUtilService.hasSomeDigits(null));
    }

    @Test
    public void testHasOnlyDigits(){
        assertEquals(RegexUtilService.cutAllDigits("311df3131"), List.of("311","3131"));
        assertEquals(RegexUtilService.cutAllDigits(""), new ArrayList<>());
        assertEquals(RegexUtilService.cutAllDigits(null), null);
    }

}
