package de.cristiano.marathon.daily;

/**
 * Merge two sorted arrays
 */
class Day5 {

    int[] solution(int[] arrayA, int[] arrayB) {
        final int[] mergedArray = new int[arrayA.length + arrayB.length];
        int indexA = 0;
        int indexB = 0;

        for (int index = 0; index < mergedArray.length; index++) {
            int nextElement;

            if (indexA == arrayA.length) {
                nextElement = arrayB[indexB];
                indexB++;
            } else if (indexB == arrayB.length) {
                nextElement = arrayA[indexA];
                indexA++;
            } else if (arrayA[indexA] < arrayB[indexB]) {
                nextElement = arrayA[indexA];
                indexA++;
            } else {
                nextElement = arrayB[indexB];
                indexB++;
            }

            mergedArray[index] = nextElement;
        }

        return mergedArray;
    }

}
