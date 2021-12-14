package com.tedchoward.aoc2021.dec12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.val;
import org.junit.jupiter.api.Test;

public class PathFinderTest {

    @Test
    public void testExample1() {
        val inputStr =
            """
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countPaths();
        assertEquals(10, result);
    }

    @Test
    public void testExample2() {
        val inputStr =
            """
                dc-end
                HN-start
                start-kj
                dc-start
                dc-HN
                LN-dc
                HN-end
                kj-sa
                kj-HN
                kj-dc""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countPaths();
        assertEquals(19, result);
    }

    @Test
    public void testExample3() {
        val inputStr =
            """
                fs-end
                he-DX
                fs-he
                start-DX
                pj-DX
                end-zg
                zg-sl
                zg-pj
                pj-he
                RW-he
                fs-DX
                pj-RW
                zg-RW
                start-pj
                he-WI
                zg-he
                pj-fs
                start-RW""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countPaths();
        assertEquals(226, result);
    }

    @Test
    public void testPuzzle1() {
        val inputStr =
            """
                cz-end
                cz-WR
                TD-end
                TD-cz
                start-UM
                end-pz
                kb-UM
                mj-UM
                cz-kb
                WR-start
                WR-pz
                kb-WR
                TD-kb
                mj-kb
                TD-pz
                UM-pz
                kb-start
                pz-mj
                WX-cz
                sp-WR
                mj-WR""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countPaths();
        assertEquals(3450, result);
    }

    @Test
    public void testExample1_pt2() {
        val inputStr =
            """
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countMorePaths();
        assertEquals(36, result);
    }

    @Test
    public void testExample2_pt2() {
        val inputStr =
            """
                dc-end
                HN-start
                start-kj
                dc-start
                dc-HN
                LN-dc
                HN-end
                kj-sa
                kj-HN
                kj-dc""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countMorePaths();
        assertEquals(103, result);
    }

    @Test
    public void testExample3_pt2() {
        val inputStr =
            """
                fs-end
                he-DX
                fs-he
                start-DX
                pj-DX
                end-zg
                zg-sl
                zg-pj
                pj-he
                RW-he
                fs-DX
                pj-RW
                zg-RW
                start-pj
                he-WI
                zg-he
                pj-fs
                start-RW""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countMorePaths();
        assertEquals(3509, result);
    }

    @Test
    public void testPuzzle2() {
        val inputStr =
            """
                cz-end
                cz-WR
                TD-end
                TD-cz
                start-UM
                end-pz
                kb-UM
                mj-UM
                cz-kb
                WR-start
                WR-pz
                kb-WR
                TD-kb
                mj-kb
                TD-pz
                UM-pz
                kb-start
                pz-mj
                WX-cz
                sp-WR
                mj-WR""";

        val parsedInput = inputStr.lines().toList();
        val pathFinder = new PathFinder(parsedInput);

        val result = pathFinder.countMorePaths();
        assertEquals(3450, result);
    }
}
