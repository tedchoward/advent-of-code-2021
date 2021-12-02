package com.tedchoward.aoc2021.dec01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncreaseCounterTest {

    private IncreaseCounter increaseCounter;

    @BeforeEach
    public void setup() {
        increaseCounter = new IncreaseCounter();
    }

    @Test
    public void testIncreaseCounterSimple() {
        val depths = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        val increaseCount = increaseCounter.countIncreases(depths);

        assertEquals(7, increaseCount);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val filePath = Paths.get("src/main/resources", "input.txt");
        val depths = Files.readAllLines(filePath).stream().map(Integer::parseInt).toList();
        val increaseCounter = new IncreaseCounter();

        assertEquals(1301, increaseCounter.countIncreases(depths));
    }

    @Test
    public void testThreeCountsSimple() {
        val depths = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

        val increaseCount = increaseCounter.countThreeIncreases(depths);

        assertEquals(5, increaseCount);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val filePath = Paths.get("src/main/resources", "input.txt");
        val depths = Files.readAllLines(filePath).stream().map(Integer::parseInt).toList();
        val increaseCounter = new IncreaseCounter();

        val increaseCount = increaseCounter.countThreeIncreases(depths);

        System.out.println(increaseCount);

        assertEquals(1346, increaseCounter.countThreeIncreases(depths));
    }
}
