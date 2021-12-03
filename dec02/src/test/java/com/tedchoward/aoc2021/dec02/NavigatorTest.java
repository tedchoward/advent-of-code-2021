package com.tedchoward.aoc2021.dec02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import lombok.val;
import org.junit.jupiter.api.Test;

public class NavigatorTest {

    @Test
    public void testTrackCourseSimple() {
        val input =
            """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2""";

        val parsedInput = parseInput(input);

        val navigator = new Navigator();
        val result = navigator.trackCourse(parsedInput);
        assertEquals(150, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val inputString = Files.readString(filePath);
        val parsedInput = parseInput(inputString);

        val navigator = new Navigator();
        val result = navigator.trackCourse(parsedInput);
        assertEquals(2102357, result);
    }

    @Test
    public void testTrackAimedCourseSimple() {
        val input =
            """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2""";

        val parsedInput = parseInput(input);

        val navigator = new Navigator();
        val result = navigator.trackAimedCourse(parsedInput);
        assertEquals(900, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val inputString = Files.readString(filePath);
        val parsedInput = parseInput(inputString);

        val navigator = new Navigator();
        val result = navigator.trackAimedCourse(parsedInput);
        assertEquals(2101031224, result);
    }

    private List<Navigator.Command> parseInput(String input) {
        return input
            .lines()
            .map(str -> {
                val parts = str.split(" ");
                val direction = Navigator.Direction.valueOf(parts[0].toUpperCase(Locale.ROOT));
                val units = Integer.parseInt(parts[1]);
                return new Navigator.Command(direction, units);
            })
            .toList();
    }
}
