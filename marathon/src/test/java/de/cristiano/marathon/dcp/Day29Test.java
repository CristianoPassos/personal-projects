package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day29Test {

    private final Day29 day29 = new Day29();

    @Test
    public void deckShuffle_shouldSucceed() {
        //Given
        final String[] deck = {"Q", "W", "E", "R", "T", "U", "I", "O", "P", "Ü",
                "Q", "W", "E", "R", "T", "U", "I", "O", "P", "Ü",
                "Q", "W", "E", "R", "T", "U", "I", "O", "P", "Ü",
                "Q", "W", "E", "R", "T", "U", "I", "O", "P", "Ü",
                "Q", "W", "E", "R", "T", "U", "I", "O", "P", "Ü",
                "Q", "W"};

        //When

        final String[] shuffledDeck = day29.shuffle(deck, 3);

        //Then
        assertThat(shuffledDeck, not(arrayContaining(deck)));
    }

}
