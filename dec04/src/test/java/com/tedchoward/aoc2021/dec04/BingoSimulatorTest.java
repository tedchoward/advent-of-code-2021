package com.tedchoward.aoc2021.dec04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;

public class BingoSimulatorTest {

    @Test
    public void determineWinningScoreTestSimple() {
        val input =
            """
                7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                                
                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19
                                
                 3 15  0  2 22
                 9 18 13 17  5
                19  8  7 25 23
                20 11 10 24  4
                14 21 16 12  6
                                
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7""";

        val parseInput = parseInput(input);

        val bingoSimulator = new BingoSimulator();
        val winningScore = bingoSimulator.determineWinningScore(parseInput.numbersToDraw(), parseInput.bingoCardData());

        assertEquals(4512, winningScore);
    }

    @Test
    public void testPuzzle1() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val input = Files.readString(filePath);

        val parseInput = parseInput(input);

        val bingoSimulator = new BingoSimulator();
        val winningScore = bingoSimulator.determineWinningScore(parseInput.numbersToDraw(), parseInput.bingoCardData());

        assertEquals(11774, winningScore);
    }

    @Test
    public void determineLastWinningScoreTestSimple() {
        val input =
            """
                    7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                                    
                    22 13 17 11  0
                     8  2 23  4 24
                    21  9 14 16  7
                     6 10  3 18  5
                     1 12 20 15 19
                                    
                     3 15  0  2 22
                     9 18 13 17  5
                    19  8  7 25 23
                    20 11 10 24  4
                    14 21 16 12  6
                                    
                    14 21 17 24  4
                    10 16 15  9 19
                    18  8 23 26 20
                    22 11 13  6  5
                     2  0 12  3  7""";

        val parseInput = parseInput(input);

        val bingoSimulator = new BingoSimulator();
        val winningScore = bingoSimulator.determineLastWinningScore(
            parseInput.numbersToDraw(),
            parseInput.bingoCardData()
        );

        assertEquals(1924, winningScore);
    }

    @Test
    public void testPuzzle2() throws IOException {
        val filePath = Paths.get("src/test/resources", "input.txt");
        val input = Files.readString(filePath);

        val parseInput = parseInput(input);

        val bingoSimulator = new BingoSimulator();
        val winningScore = bingoSimulator.determineLastWinningScore(
            parseInput.numbersToDraw(),
            parseInput.bingoCardData()
        );

        assertEquals(4495, winningScore);
    }

    record ParsedInput(List<Integer> numbersToDraw, List<String> bingoCardData) {}

    private ParsedInput parseInput(String input) {
        val parts = input.split("\\n\\n");
        val numbersToDraw = Arrays.stream(parts[0].split(",")).map(Integer::parseInt).toList();
        final List<String> bingoCardData = new ArrayList<>();
        for (int i = 1, cnt = parts.length; i < cnt; i++) {
            bingoCardData.add(parts[i]);
        }

        return new ParsedInput(numbersToDraw, bingoCardData);
    }
}
