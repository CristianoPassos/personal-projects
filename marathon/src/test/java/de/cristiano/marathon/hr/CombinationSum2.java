package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CombinationSum2 {

    @Test
    void subSets_shouldSucceed() {
        //Given
        final int[] candidates = {3, 1, 3, 5, 1, 1};

        //When
        final List<List<Integer>> combinations = combinationSum2(candidates, 8);

        //Then
        assertThat(combinations.size(), is(3));
    }

    List<List<Integer>> combinationSum2(int[] candidates, int target) {
        final List<List<Integer>> combinations = new ArrayList<>();
        final List<Integer> currentCombination = new ArrayList<>();

        Arrays.sort(candidates);

        doAllCombinations(candidates, target, 0, combinations, currentCombination);

        return combinations;
    }


    private int doAllCombinations(int[] candidates, int target, int index, List<List<Integer>> combinations, List<Integer> currentCombination) {
        if (target == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return target;
        }

        if (target < 0 || index >= candidates.length) {
            return target;
        }

        for (int innerIndex = index; innerIndex < candidates.length; innerIndex++) {
            int currentValue = candidates[innerIndex];

            if (innerIndex == index || candidates[innerIndex - 1] != currentValue) {
                currentCombination.add(currentValue);

                if (doAllCombinations(candidates, target - currentValue, innerIndex + 1, combinations, currentCombination) < 0) {
                    currentCombination.remove(currentCombination.size() - 1);
                    return target;
                }

                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        return target;
    }
}
