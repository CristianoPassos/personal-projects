package de.cristiano.marathon.miscellaneous;

public class BinarySearch {

    public boolean isPresent(int[] array, int query) {
        int max = array.length - 1;
        int min = 0;

        while (min <= max) {
            final int average = (max + min) / 2;

            final int element = array[average];

            if (element == query) {
                return true;
            } else if (query > element) {
                min = average + 1;
            } else {
                max = average - 1;
            }
        }


        return false;
    }
}
