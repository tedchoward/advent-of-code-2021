package com.tedchoward.aoc2021.dec03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.val;
import org.junit.jupiter.api.Test;

public class DiagnosticReporterTest {

    @Test
    public void calculatePowerConsumptionTestSimple() {
        val input =
            """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010""";

        val parsedInput = input.lines().map(num -> Integer.parseInt(num, 2)).toList();
        val diagnosticReporter = new DiagnosticReporter();
        val response = diagnosticReporter.calculatePowerConsumption(parsedInput);

        assertEquals(198, response);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val parsedInput = Files.readAllLines(filePath).stream().map(num -> Integer.parseInt(num, 2)).toList();

        val diagnosticReporter = new DiagnosticReporter();
        val response = diagnosticReporter.calculatePowerConsumption(parsedInput);

        assertEquals(2035764, response);
    }

    @Test
    public void testCalculateLifeSupportRatingSimple() {
        val input =
            """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010""";

        val parsedInput = input.lines().map(num -> Integer.parseInt(num, 2)).toList();
        val diagnosticReporter = new DiagnosticReporter();
        val response = diagnosticReporter.calculateLifeSupportRating(parsedInput);

        assertEquals(230, response);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val parsedInput = Files.readAllLines(filePath).stream().map(num -> Integer.parseInt(num, 2)).toList();

        val diagnosticReporter = new DiagnosticReporter();
        val response = diagnosticReporter.calculateLifeSupportRating(parsedInput);

        assertEquals(2817661, response);
    }
}
