package com.tedchoward.aoc2021.dec07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Test;

public class CrabAlignerTest {

    @Test
    public void calculateOptimumAlignemntTest() {
        val inputStr = "16,1,2,0,4,2,7,1,2,14";
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val crabAligner = new CrabAligner();
        val result = crabAligner.calculateOptimumAlignemnt(parsedInput);
        assertEquals(37, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val crabAligner = new CrabAligner();
        val result = crabAligner.calculateOptimumAlignemnt(parsedInput);
        assertEquals(37, result);
    }
}
