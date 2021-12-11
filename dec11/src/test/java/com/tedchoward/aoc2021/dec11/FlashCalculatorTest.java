package com.tedchoward.aoc2021.dec11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Test;

public class FlashCalculatorTest {

    @Test
    public void countFlashesSimpleTest() {
        val inputStr =
            """
                5483143223
                2745854711
                5264556173
                6141336146
                6357385478
                4167524645
                2176841721
                6882881134
                4846848554
                5283751526""";
        val parsedInput = inputStr
            .lines()
            .map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
            .toList();

        val flashCalculator = new FlashCalculator(parsedInput);
        val result = flashCalculator.countFlashes(100);

        assertEquals(1656, result);
    }

    @Test
    public void testPuzzle1() {
        val inputStr =
            """
                4781623888
                1784156114
                3265645122
                4371551414
                3377154886
                7882314455
                6421348681
                7175424287
                5488242184
                2448568261""";

        val parsedInput = inputStr
            .lines()
            .map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
            .toList();

        val flashCalculator = new FlashCalculator(parsedInput);
        val result = flashCalculator.countFlashes(100);

        assertEquals(1713, result);
    }

    @Test
    public void findFirstAllFlashCycleSimpleTest() {
        val inputStr =
            """
                5483143223
                2745854711
                5264556173
                6141336146
                6357385478
                4167524645
                2176841721
                6882881134
                4846848554
                5283751526""";
        val parsedInput = inputStr
            .lines()
            .map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
            .toList();

        val flashCalculator = new FlashCalculator(parsedInput);
        val result = flashCalculator.findFirstAllFlashCycle();

        assertEquals(195, result);
    }

    @Test
    public void testPuzzle2() {
        val inputStr =
            """
                4781623888
                1784156114
                3265645122
                4371551414
                3377154886
                7882314455
                6421348681
                7175424287
                5488242184
                2448568261""";

        val parsedInput = inputStr
            .lines()
            .map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
            .toList();

        val flashCalculator = new FlashCalculator(parsedInput);
        val result = flashCalculator.findFirstAllFlashCycle();

        assertEquals(502, result);
    }
}
