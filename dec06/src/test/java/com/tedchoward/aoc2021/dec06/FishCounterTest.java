package com.tedchoward.aoc2021.dec06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import lombok.val;
import org.junit.jupiter.api.Test;

public class FishCounterTest {

    @Test
    public void simpleTest() {
        val inputStr = "2";
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val fishCounter = new FishCounter();
        val result = fishCounter.calculateFish(parsedInput, 19);

        assertEquals(7, result);
    }

    @Test
    public void countFishFor80DaysSimpleTest() {
        val inputStr = "3,4,3,1,2";
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val fishCounter = new FishCounter();
        val result = fishCounter.calculateFish(parsedInput, 80);

        assertEquals(5934, result);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val fishCounter = new FishCounter();
        val result = fishCounter.calculateFish(parsedInput, 80);

        assertEquals(379414, result);
    }

    @Test
    public void countFishFor256DaysSimpleTest() {
        val inputStr = "3,4,3,1,2";
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val fishCounter = new FishCounter();
        val result = fishCounter.calculateFish(parsedInput, 256);

        assertEquals(26_984_457_539L, result);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val inputStr = Files.readString(Paths.get("src/test/resources", "input.txt"));
        val parsedInput = Arrays.stream(inputStr.split(",")).map(Integer::parseInt).toList();

        val fishCounter = new FishCounter();
        val result = fishCounter.calculateFish(parsedInput, 256);

        assertEquals(1_705_008_653_296L, result);
    }
}
