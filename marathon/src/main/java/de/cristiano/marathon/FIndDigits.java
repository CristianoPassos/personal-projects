package de.cristiano.marathon;

import java.util.Scanner;

public class FIndDigits {
	static int findDigits(int n) {
		int[] digits = extractDigits(n);
		return calculateEvenlyDisisibleDigits(n, digits);
	}

	private static int calculateEvenlyDisisibleDigits(int n, int[] digits) {
		int evenlyDivisibleDigits = 0;
		for (int i = 0; i < digits.length; i++) {
			int j = digits[i];
			if (j != 0 && n % j == 0) {
				evenlyDivisibleDigits++;
			}
		}
		return evenlyDivisibleDigits;
	}

	private static int[] extractDigits(int n) {
		char[] numbers = String.valueOf(n).toCharArray();
		int[] digits = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			digits[i] = Character.getNumericValue(numbers[i]);
		}
		return digits;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			int n = in.nextInt();
			int result = findDigits(n);
			System.out.println(result);
		}
		in.close();
	}
}
