package de.cristiano.marathon.daily;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given a string, find the longest palindromic contiguous substring.
 * If there are more than one with the maximum length, return any one.
 */
public class Day24 {


    String longestPalindromic(String string) {

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (Character character : string.toCharArray()) {
            characterFrequency.compute(character, (k, v) -> {
                if (v == null)
                    return 1;
                v++;
                return v;
            });
        }

        final List<Map.Entry<Character, Integer>> evens = characterFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 0)
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(2)
                .collect(Collectors.toList());

        if (evens.size() != 2) {
            throw new InputMismatchException("Not a Palindrome");
        }

        final Map.Entry<Character, Integer> odd = characterFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 1)
                .min((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .orElseThrow(() -> new InputMismatchException("Not a Palindrome"));

        StringBuilder longestPalindrome = new StringBuilder();

        repeat(evens.get(0).getKey(), longestPalindrome, evens.get(0).getValue() / 2);
        repeat(evens.get(1).getKey(), longestPalindrome, evens.get(1).getValue() / 2);
        repeat(odd.getKey(), longestPalindrome, odd.getValue());
        repeat(evens.get(1).getKey(), longestPalindrome, evens.get(1).getValue() / 2);
        repeat(evens.get(0).getKey(), longestPalindrome, evens.get(0).getValue() / 2);

        return longestPalindrome.toString();
    }

    private void repeat(Character character, StringBuilder longestPalindrome, int times) {
        for (int repeat = 0; repeat < times; repeat++) {
            longestPalindrome.append(character);
        }
    }
}