package de.cristiano.marathon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    private Day9 day9 = new Day9();

    @Test
    void countUnivalSubtrees() {
        //Given
        Day9.Node root = new Day9.Node(new Day9.Node("1"), new Day9.Node(new Day9.Node(new Day9.Node("1"), new Day9.Node("1"), "1"), new Day9.Node("0"), "0"), "0");


        //When
        int univalSubtress = day9.countUnivalSubtrees(root);

        //Then
        assertEquals(5, univalSubtress);
    }
}