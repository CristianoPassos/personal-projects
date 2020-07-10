package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class PermutationTest {

    private final Permutation permutation = new Permutation();

    class Solution {
        public int maxArea(int[] height) {
            int maxArea = Integer.MIN_VALUE;

            for (int left = 0; left < height.length; left++) {
                int right = height.length - 1;

                do {
                    int currentArea = Math.min(height[left], height[right]) * (right - left);

                    maxArea = Math.max(maxArea, currentArea);

                    if (height[right] > height[left]) {
                        break;
                    }

                    right--;
                } while (left < right);

            }
            return maxArea;
        }
    }

    @Test
    void letterCombinations_shouldSucceed() {
        //When
        final List<String> permutation = this.permutation.letterCombinations("23");

        //Then
        assertThat(permutation, contains("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
    }
}
