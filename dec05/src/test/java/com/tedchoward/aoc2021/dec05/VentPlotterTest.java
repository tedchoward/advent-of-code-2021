package com.tedchoward.aoc2021.dec05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.val;
import org.junit.jupiter.api.Test;

public class VentPlotterTest {

    @Test
    public void countOverlappingPointsSimpleTest() {
        val inputStr =
            """
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2""";

        val parsedInput = parseInput(inputStr);

        val ventPlotter = new VentPlotter();
        val result = ventPlotter.countOverlappingPoints(parsedInput);
        assertEquals(5, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = parseInput(inputStr);

        val ventPlotter = new VentPlotter();
        val result = ventPlotter.countOverlappingPoints(parsedInput);
        assertEquals(6710, result);
    }

    @Test
    public void countAllOverlappingPointsSimpleTest() {
        val inputStr =
            """
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2""";

        val parsedInput = parseInput(inputStr);

        val ventPlotter = new VentPlotter();
        val result = ventPlotter.countAllOverlappingPoints(parsedInput);
        assertEquals(12, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = parseInput(inputStr);

        val ventPlotter = new VentPlotter();
        val result = ventPlotter.countAllOverlappingPoints(parsedInput);
        assertEquals(20121, result);
    }

    private static final Pattern LINE_PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    private List<VentPlotter.Line> parseInput(String inputStr) {
        return inputStr
            .lines()
            .map(LINE_PATTERN::matcher)
            .filter(Matcher::find)
            .map(m -> {
                val startX = Integer.parseInt(m.group(1));
                val startY = Integer.parseInt(m.group(2));
                val endX = Integer.parseInt(m.group(3));
                val endY = Integer.parseInt(m.group(4));
                val start = new VentPlotter.Point(startX, startY);
                val end = new VentPlotter.Point(endX, endY);
                return new VentPlotter.Line(start, end);
            })
            .toList();
    }
}
