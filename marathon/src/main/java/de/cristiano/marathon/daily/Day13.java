package de.cristiano.marathon.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * The power set of a set is the set of all its subsets. Write a function that, given a set, generates its power set.
 * <p/>
 * For example, given the set {1, 2, 3}, it should return {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.
 * <p/>
 * You may also use a list or array to represent a set.
 */
public class Day13 {

    Set<Set<Integer>> powerSet(Set<Integer> set) {
        Set<Set<Integer>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>());

        if (set.isEmpty()) {
            return powerSet;
        }


        set.forEach(number -> {
            Set<Set<Integer>> tempPowerSet = new HashSet<>();
            powerSet.forEach(subSet -> {
                HashSet<Integer> newSubSet = new HashSet<>(subSet);
                newSubSet.add(number);
                tempPowerSet.add(newSubSet);
            });

            powerSet.addAll(tempPowerSet);
            final HashSet<Integer> newSet = new HashSet<>();
            set.add(number);
            powerSet.add(newSet);
        });

        return powerSet;
    }
}
