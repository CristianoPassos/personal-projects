package br.com.cristiano.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MissingNumbers {

	static int[] missingNumbers(int[] arr, int[] brr) {
		Set<Integer> uniqueNumbers = convertToset(arr);
		Set<Integer> missingNumbers = findMissingNumber(arr, brr, uniqueNumbers);
		int[] missingNumbersArray = missingNumbers.stream().mapToInt(i -> i).toArray();
		Arrays.sort(missingNumbersArray);
		return missingNumbersArray;
	}

	private static Set<Integer> convertToset(int[] arr) {
		Set<Integer> set = new HashSet<>();
		for (int i : arr) {
			set.add(i);
		}
		return set;
	}

	private static Set<Integer> findMissingNumber(int[] arr, int[] brr, Set<Integer> uniqueNumbers) {
		Set<Integer> missingNumbers = new HashSet<>();
		for (Integer number : uniqueNumbers) {
			int frequencyA = countFrequency(arr, number);
			int frequencyB = countFrequency(brr, number);
			if (frequencyB > frequencyA) {
				missingNumbers.add(number);
			}
		}
		return missingNumbers;
	}

	private static int countFrequency(int[] brr, Integer number) {
		int frequency = 0;
		for (int i = 0; i < brr.length; i++) {
			int j = brr[i];
			if (j == number) {
				frequency++;
			}
		}
		return frequency;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		int m = in.nextInt();
		int[] brr = new int[m];
		for (int brr_i = 0; brr_i < m; brr_i++) {
			brr[brr_i] = in.nextInt();
		}
		int[] result = missingNumbers(arr, brr);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
		}
		System.out.println("");

		in.close();
	}
}
