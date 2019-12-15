package de.cristiano.marathon.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of integers S and a target number k, write a function that returns a subset of S that adds up to k.
 * If such a subset cannot be made, then return null.
 */
public class Day30 {

    public Integer[] addsUpSubSet(Integer[] numbers, int addsTo) {

        for (int index = 0; index < numbers.length; index++) {
            final int searchFor = addsTo - numbers[index];
            final Set<Integer> excludeIndex = new HashSet<>();

            excludeIndex.add(index);

            final Integer[] subArray = addsUpSubSet(numbers, searchFor, excludeIndex);

            if (subArray != null) {
                return add(subArray, numbers[index]);
            }
        }

        return null;
    }

    private Integer[] addsUpSubSet(Integer[] numbers, int addsTo, Set<Integer> excludeIndex) {

        if (addsTo == 0) {
            return new Integer[]{};
        } else if (addsTo < 0) {
            return null;
        }

        final Set<Integer> toExclude = new HashSet<>(excludeIndex);

        for (int index = 0; index < numbers.length; index++) {
            if (!excludeIndex.contains(index)) {
                final int searchFor = addsTo - numbers[index];

                toExclude.add(index);

                final Integer[] subSet = addsUpSubSet(numbers, searchFor, toExclude);

                if (subSet != null) {
                    return add(subSet, numbers[index]);
                }
            }
        }

        return null;
    }


    private Integer[] add(Integer[] array, int add) {
        final Integer[] newArray = Arrays.copyOf(array, array.length + 1);

        newArray[newArray.length - 1] = add;

        return newArray;
    }

}
