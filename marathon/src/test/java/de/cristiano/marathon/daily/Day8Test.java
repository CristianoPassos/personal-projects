package de.cristiano.marathon.daily;

import de.cristiano.marathon.daily.Day8;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    @Test
    void hashTable_shouldSucceed() {
        //Given
        final Day8.HashTable myHashTable = new Day8.HashTable(50);

        myHashTable.set(10000);
        myHashTable.set("grapes");
        myHashTable.set("apples");
        myHashTable.set(9);

        //When
        final Object apples = myHashTable.get("apples");
        final Object grapes = myHashTable.get("grapes");

        //Then
        assertEquals("apples", apples);
        assertEquals("grapes", grapes);
    }

}