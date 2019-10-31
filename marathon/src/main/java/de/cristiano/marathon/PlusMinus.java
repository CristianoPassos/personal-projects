package de.cristiano.marathon;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PlusMinus {
	static void plusMinus(int[] arr) {
		DecimalFormat sixCaseFormatter = new DecimalFormat("#0.000000");
		// Complete this function
		double fractionPositive = 0d;
		double fractionNegative = 0d;
		double fractionZero = 0d;

		double quantityPositive = 0d;
		double quantityNegative = 0d;
		double quantityZero = 0d;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				quantityPositive++;
				continue;
			}
			if (arr[i] < 0) {
				quantityNegative++;
				continue;
			}
			quantityZero++;
		}

		fractionPositive = quantityPositive / arr.length;
		fractionNegative = quantityNegative / arr.length;
		fractionZero = quantityZero / arr.length;

		System.out.println(sixCaseFormatter.format(fractionPositive));
		System.out.println(sixCaseFormatter.format(fractionNegative));
		System.out.println(sixCaseFormatter.format(fractionZero));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int arr_i = 0; arr_i < n; arr_i++) {
			arr[arr_i] = in.nextInt();
		}
		plusMinus(arr);
		in.close();
	}
}
