package de.cristiano.marathon.hr;

import java.util.Scanner;

public class Staircase {
	private static String buildCase(int i, int n) {
		StringBuilder sb = new StringBuilder();
		int spaces = n - (i + 1);
		int dash = n - spaces;
		fill(sb, " ", spaces);
		fill(sb, "#", dash);
		return sb.toString();
	}

	private static void fill(StringBuilder sb, String character, int quantity) {
		for (int i = 0; i < quantity; i++) {
			sb.append(character);
		}
	}

	static void staircase(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(buildCase(i, n));
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		staircase(n);
		in.close();
	}
}
