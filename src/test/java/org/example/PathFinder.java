package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.example.PathFinder.hasPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathFinder extends DefaultTest {

    @Test
    public void existentPathTest() {
        // cat->cot->cog->fod->fog->dog
        List<String> lists = new ArrayList<>(asList("fog", "fod", "pog", "kog", "dod", "cot", "cog", "dog"));
        assertTrue(hasPath(lists, "cat", "dog"));
    }

    @Test
    public void nonExistentPathTest() {
        List<String> lists = new ArrayList<>(asList("lal", "fog", "pog", "kog", "dod", "cot", "cap", "cog"));
        assertFalse(hasPath(lists, "cat", "dog"));
    }

}
