package br.com.cristiano.easy;

import org.junit.jupiter.api.Test;

import static br.com.cristiano.easy.JumpingClouds.jumpingOnClouds;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpingCloudsTest {

    @Test
    void testCase1() {
        int[] clouds = {0, 0, 1, 0, 0, 1, 0};
        int jumps = jumpingOnClouds(clouds);
        assertEquals(4, jumps);
    }

    @Test
    void testCase2() {
        int[] clouds = {0, 0, 0, 1, 0, 0};
        int jumps = jumpingOnClouds(clouds);
        assertEquals(3, jumps);
    }

    @Test
    void testCase3() {
        int[] clouds = {0, 0, 1, 0, 0, 0, 0, 1, 0, 0};
        int jumps = jumpingOnClouds(clouds);
        assertEquals(6, jumps);
    }

    @Test
    void testCase8() {
        int[] clouds = {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0};
        int jumps = jumpingOnClouds(clouds);
        assertEquals(46, jumps);
    }
}