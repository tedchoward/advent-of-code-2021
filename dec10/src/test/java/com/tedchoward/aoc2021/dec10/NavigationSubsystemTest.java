package com.tedchoward.aoc2021.dec10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.val;
import org.junit.jupiter.api.Test;

public class NavigationSubsystemTest {

    @Test
    public void scoreSyntaxErrorsSimpleTest() {
        val inputStr =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
                (((({<>}<{<{<>}{[]{[]{}
                [[<[([]))<([[{}[[()]]]
                [{[{({}]{}}([{[{{{}}([]
                {<[[]]>}<{[{[{[]{()[[[]
                [<(<(<(<{}))><([]([]()
                <{([([[(<>()){}]>(<<{{
                <{([{{}}[<[[[<>{}]]]>[]]""";

        val parsedInput = inputStr.lines().toList();

        val navigattionSubSystem = new NavigationSubSystem();
        val result = navigattionSubSystem.scoreCorruptedLineErrors(parsedInput);

        assertEquals(26397, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = inputStr.lines().toList();

        val navigattionSubSystem = new NavigationSubSystem();
        val result = navigattionSubSystem.scoreCorruptedLineErrors(parsedInput);

        assertEquals(367227, result);
    }

    @Test
    public void scoreIncompleteLineErrorsSimpleTest() {
        val inputStr =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
                (((({<>}<{<{<>}{[]{[]{}
                [[<[([]))<([[{}[[()]]]
                [{[{({}]{}}([{[{{{}}([]
                {<[[]]>}<{[{[{[]{()[[[]
                [<(<(<(<{}))><([]([]()
                <{([([[(<>()){}]>(<<{{
                <{([{{}}[<[[[<>{}]]]>[]]""";

        val parsedInput = inputStr.lines().toList();

        val navigattionSubSystem = new NavigationSubSystem();
        val result = navigattionSubSystem.scoreIncompleteLineErrors(parsedInput);

        assertEquals(288957, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));

        val parsedInput = inputStr.lines().toList();

        val navigattionSubSystem = new NavigationSubSystem();
        val result = navigattionSubSystem.scoreIncompleteLineErrors(parsedInput);

        assertEquals(3583341858L, result);
    }
}
