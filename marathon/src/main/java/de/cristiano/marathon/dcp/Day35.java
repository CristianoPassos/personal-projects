package de.cristiano.marathon.dcp;

/**
 * Given such an array, find the index of the element in the array in faster than linear time.
 * If the element doesn't exist in the array, return null.
 */
public class Day35 {

    int find(int[] rotatedArray, int search) {
        int pivot = findPivot(rotatedArray, 0, rotatedArray.length);


        if (rotatedArray[pivot] == search) {
            return pivot;
        }

        if (rotatedArray[pivot] > search) {
            return binarySearch(rotatedArray, pivot + 1, rotatedArray.length - 1, search);
        }

        return binarySearch(rotatedArray, 0, pivot - 1, search);
    }

    private int binarySearch(int[] rotatedArray, int indexStart, int indexEnd, int search) {
        if (indexStart > indexEnd) {
            return -1;
        }

        int middle = (indexStart + indexEnd) / 2;

        if (search == rotatedArray[middle]) {
            return middle;
        }

        if (search > rotatedArray[middle]) {
            return binarySearch(rotatedArray, middle + 1, indexEnd, search);
        }

        return binarySearch(rotatedArray, indexStart, middle - 1, search);
    }

    int findPivot(int[] rotatedArray, int indexStart, int indexEnd) {
        if (indexEnd < indexStart) {
            return -1;
        }

        if (indexStart == indexEnd) {
            return indexEnd;
        }

        int middle = (indexStart + indexEnd) / 2;

        if (middle < indexEnd && rotatedArray[middle] > rotatedArray[middle + 1]) {
            return middle;
        }

        if (middle > indexStart && rotatedArray[middle] < rotatedArray[middle - 1]) {
            return middle - 1;
        }

        if (rotatedArray[indexStart] >= rotatedArray[middle]) {
            return findPivot(rotatedArray, indexStart, middle - 1);
        }

        return findPivot(rotatedArray, middle + 1, indexEnd);

    }
}
