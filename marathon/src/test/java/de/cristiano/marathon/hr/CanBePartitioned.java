package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CanBePartitioned {

    @Test
    public void baseCase_shouldSucceed() {
        //Given
        int[] A = {1, 2, 3, 4, 5, 6, 7};

        //When
        final boolean canPartition = canPartition(A);

        //Then
        assertThat(canPartition, is(true));
    }

    public boolean canPartition(int[] nums) {
        final int sum = Arrays.stream(nums)
                .sum();

        if (sum % 2 == 1) {
            return false;
        }

        return canBePartitioned(nums, 0, sum / 2, new HashMap<>());
    }

    boolean canBePartitioned(int[] nums, int index, int target, Map<String, Boolean> cache) {

        for (int innerIndex = index; innerIndex < nums.length; innerIndex++) {
            int newTarget = target - nums[innerIndex];
            String cacheKey = index + " " + newTarget;

            if (newTarget == 0) {
                return true;
            }

            if (newTarget < 0) {
                return false;
            }

            if (!cache.containsKey(cacheKey)) {
                cache.put(cacheKey, canBePartitioned(nums, innerIndex + 1, newTarget, cache));
            }

            if (cache.get(cacheKey)) {
                return true;
            }

        }

        return false;
    }
}
