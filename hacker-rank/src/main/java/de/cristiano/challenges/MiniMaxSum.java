package de.cristiano.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MiniMaxSum {
	static void miniMaxSum(int[] arr) {
		List<Integer> numbers = convertToList(arr);
		Collections.sort(numbers);
		long maxSum = getMaxSum(numbers);
		long minSum = getMinSum(numbers);
		System.out.println(minSum + " " + maxSum);

	}

	private static long getMinSum(List<Integer> numbers) {
		long minSum = 0;
		int lastIndex = numbers.size() - 1;
		for (int i = 0; i < lastIndex; i++) {
			minSum += numbers.get(i);
		}
		return minSum;
	}

	private static long getMaxSum(List<Integer> numbers) {
		long maxSum = 0;
		for (int i = 1; i < numbers.size(); i++) {
			maxSum += numbers.get(i);
		}
		return maxSum;
	}

	private static List<Integer> convertToList(int[] arr) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i];
			numbers.add(j);
		}
		return numbers;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] arr = new int[5];
		for (int arr_i = 0; arr_i < 5; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		miniMaxSum(arr);
		in.close();
	}
}
