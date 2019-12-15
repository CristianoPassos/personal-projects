package de.cristiano.marathon.daily;

import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Implement an autocomplete system. That is, given a query string s and a set of all possible query strings,
 * return all strings in the set that have s as a prefix.
 * <p>
 * For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
 */
public class Day70 {

    private final Trie trie = new Trie(null);

    void ingest(String[] words) {
        for (String word : words) {
            trie.add(word);
        }
    }

    String[] search(String query) {
        return trie.search(query);
    }

    private static class Trie {
        Character value;

        Map<Character, Trie> nodes = new HashMap<>();

        public Trie(Character value) {
            this.value = value;
        }

        public void add(String word) {
            add(word, 0);
        }

        private void add(String word, int currentIndex) {
            if (currentIndex == word.length()) {
                return;
            }

            final Character charAt = word.charAt(currentIndex);

            if (nodes.containsKey(charAt)) {
                nodes.get(charAt).add(word, currentIndex + 1);
            } else {
                final Trie node = new Trie(charAt);
                node.add(word, currentIndex + 1);
                nodes.put(charAt, node);
            }
        }

        public String[] search(String query) {
            return search(query, 0);
        }

        private String[] search(String query, int index) {
            if (query.length() == index) {
                return printAllWithPrefix(query.substring(0, query.length() - 1))
                        .toArray(new String[]{""});
            }

            final Trie node = nodes.get(query.charAt(index));

            if (node != null) {
                return node.search(query, index + 1);
            }

            return null;
        }

        private Set<String> printAllWithPrefix(String prefix) {
            return printAllValues().stream()
                    .map(semiWord -> prefix + semiWord)
                    .collect(toSet());
        }

        private Set<String> printAllValues() {
            if (nodes.isEmpty()) {
                final Set<String> set = new HashSet<>();
                set.add(value.toString());
                return set;
            } else {
                return nodes.values().stream()
                        .map(Trie::printAllValues)
                        .flatMap(Collection::stream)
                        .map(semiWord -> value + semiWord)
                        .collect(toSet());
            }
        }
    }
}
