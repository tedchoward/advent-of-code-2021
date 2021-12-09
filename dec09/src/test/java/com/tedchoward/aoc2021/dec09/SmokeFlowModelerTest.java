package com.tedchoward.aoc2021.dec09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;

public class SmokeFlowModelerTest {

    @Test
    public void calculateRiskLevelSimpleTest() {
        val inputStr =
            """
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678""";

        val parsedInput = parseInput(inputStr);
        val smokeFlowModeler = new SmokeFlowModeler(parsedInput);
        val result = smokeFlowModeler.calculateRiskLevel();

        assertEquals(15, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = parseInput(inputStr);
        val smokeFlowModeler = new SmokeFlowModeler(parsedInput);
        val result = smokeFlowModeler.calculateRiskLevel();

        assertEquals(585, result);
    }

    @Test
    public void findBasinsSimpleTest() {
        val inputStr =
            """
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678""";

        val parsedInput = parseInput(inputStr);
        val smokeFlowModeler = new SmokeFlowModeler(parsedInput);
        val result = smokeFlowModeler.findBasins();

        assertEquals(1134, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = parseInput(inputStr);
        val smokeFlowModeler = new SmokeFlowModeler(parsedInput);
        val result = smokeFlowModeler.findBasins();

        assertEquals(827904, result);
    }

    private List<List<Integer>> parseInput(String string) {
        return string.lines().map(l -> Arrays.stream(l.split("")).map(Integer::parseInt).toList()).toList();
    }
}
