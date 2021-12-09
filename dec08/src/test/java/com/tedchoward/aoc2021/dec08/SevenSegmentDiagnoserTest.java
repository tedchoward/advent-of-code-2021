package com.tedchoward.aoc2021.dec08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;

public class SevenSegmentDiagnoserTest {

    @Test
    public void countOnesFoursSevensAndEightsTest() {
        val inputStr =
            """
                be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
                edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
                fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
                fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
                aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
                fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
                dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
                bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
                egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
                gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce""";

        val input = parseInput(inputStr);

        val sevenSegmentDiagnoser = new SevenSegmentDiagnoser();
        val result = sevenSegmentDiagnoser.countOnesFoursSevensAndEights(input);
        assertEquals(26, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val input = parseInput(inputStr);

        val sevenSegmentDiagnoser = new SevenSegmentDiagnoser();
        val result = sevenSegmentDiagnoser.countOnesFoursSevensAndEights(input);
        assertEquals(310, result);
    }

    @Test
    public void testGetDisplayValue() {
        val inputStr = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf";
        val input = parseInputLine(inputStr);

        val sevenSegmentDiagnoser = new SevenSegmentDiagnoser();
        val result = sevenSegmentDiagnoser.getDisplayValue(input);

        assertEquals(5353, result);
    }

    @Test
    public void calculateAllDisplayValuesSimpleTest() {
        val inputStr =
            """
                be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
                edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
                fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
                fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
                aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
                fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
                dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
                bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
                egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
                gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce""";

        val input = parseInput(inputStr);

        val sevenSegmentDiagnoser = new SevenSegmentDiagnoser();
        val result = sevenSegmentDiagnoser.calculateAllDisplayValues(input);
        assertEquals(61229, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val input = parseInput(inputStr);

        val sevenSegmentDiagnoser = new SevenSegmentDiagnoser();
        val result = sevenSegmentDiagnoser.calculateAllDisplayValues(input);
        assertEquals(915941, result);
    }

    private List<SevenSegmentDiagnoser.Entry> parseInput(String inputString) {
        return inputString.lines().map(this::parseInputLine).toList();
    }

    private SevenSegmentDiagnoser.Entry parseInputLine(String inputString) {
        val parts = inputString.split("\\|");
        val entry = new SevenSegmentDiagnoser.Entry();
        Collections.addAll(entry.getSignalPatterns(), parts[0].trim().split("\\s+"));
        Collections.addAll(entry.getOutputValue(), parts[1].trim().split("\\s+"));
        return entry;
    }
}
