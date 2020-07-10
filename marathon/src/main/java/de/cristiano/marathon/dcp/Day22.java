package de.cristiano.marathon.dcp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string of round, curly, and square open and closing brackets,
 * return whether the brackets are balanced (well-formed).
 */
public class Day22 {

    private final Map<Character, Character> validElements = new HashMap<>();

    public Day22() {
        validElements.put('(', ')');
        validElements.put('[', ']');
        validElements.put('{', '}');
    }

    boolean isBalanced(String string) {
        final Stack<Character> elements = new Stack<>();

        for (Character element : string.toCharArray()) {
            if (validElements.containsKey(element)) {
                elements.push(element);
            } else {
                final Character expected = validElements.get(elements.pop());
                if (!expected.equals(element)) {
                    return false;
                }
            }
        }

        return true;
    }
}
