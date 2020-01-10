package de.cristiano.marathon.miscellaneous;

import java.util.Arrays;
import java.util.Scanner;

public class BetweenTwoSets {
    static int getTotalX(int[] a, int[] b) {
        int[] sortedA = Arrays.copyOf(a, a.length);
        int[] sortedB = Arrays.copyOf(b, b.length);
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);
        int minNumber = findMinNumber(sortedA, sortedB);
        int maxNumber = findMaxNumber(sortedA, sortedB);
        int total = calculateBetweens(a, b, minNumber, maxNumber);
        return total;
    }

    private static int calculateBetweens(int[] a, int[] b, int minNumber, int maxNumber) {
        int integersBetweenAB = 0;
        for (int i = minNumber; minNumber <= maxNumber; i++) {
            if (IsFactor(a, i) && IsFactor(b, i)) {
                integersBetweenAB++;
            }
        }
        return integersBetweenAB;
    }

    private static boolean IsFactor(int[] a, int i) {
        for (int j = 0; j < a.length; j++) {
        }
        return false;
    }

    private static int findMaxNumber(int[] sortedA, int[] sortedB) {
        int maxA = sortedA[sortedA.length - 1];
        int maxB = sortedB[sortedB.length - 1];
        if (maxB > maxA) {
            return maxB;
        }
        return maxA;
    }

    private static int findMinNumber(int[] sortedA, int[] sortedB) {
        int minA = sortedA[0];
        int minB = sortedB[0];
        if (minB < minA) {
            return minB;
        }
        return minA;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        int[] b = new int[m];
        for (int b_i = 0; b_i < m; b_i++) {
            b[b_i] = in.nextInt();
        }
        int total = getTotalX(a, b);
        System.out.println(total);
        in.close();
    }
}
