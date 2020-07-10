package de.cristiano.marathon.dcp;

/**
 * Compute the running median of a sequence of numbers.
 * That is, given a stream of numbers, print out the median of the list so far on each new element.
 * <p/>
 * Recall that the median of an even-numbered list is the average of the two middle numbers.
 * <p/>
 * For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:
 * <p><ul>
 * <li> 2</li>
 * <li> 1.5 </li>
 * <li> 2 </li>
 * <li> 3.5 </li>
 * <li> 2 </li>
 * <li> 2 </li>
 * <li> 2 </li>
 * </ul><p>
 * <p
 */
public class Day15 {

    float[] medians(int[] array) {
        float[] medians = new float[array.length];
        medians[0] = array[0];

        if (array.length == 1) {
            bubbleSort(array, 0);
            return medians;
        }

        if (array.length >= 2) {
            bubbleSort(array, 1);
            medians[1] = calculateMedian(array[0], array[1]);
        }

        for (int index = 2; index < array.length; index++) {
            bubbleSort(array, index);
            int middle = index / 2;

            if (index % 2 == 0) {
                medians[index] = array[middle];
            } else {
                medians[index] = calculateMedian(array[middle], array[middle + 1]);
            }
        }

        return medians;
    }

    private void bubbleSort(int[] array, int upTo) {
        if (upTo == 0) {
            return;
        }

        boolean isNotSorted = true;

        while (isNotSorted) {
            isNotSorted = false;

            for (int index = 1; index <= upTo; index++) {
                final int previousItem = index - 1;

                if (array[previousItem] > array[index]) {
                    final int temp = array[previousItem];
                    array[previousItem] = array[index];
                    array[index] = temp;
                    isNotSorted = true;
                }
            }
        }
    }

    private float calculateMedian(float number1, float number2) {
        return (number1 + number2) / 2;
    }
}
