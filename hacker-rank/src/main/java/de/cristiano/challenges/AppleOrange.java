package de.cristiano.challenges;

import java.util.Scanner;

public class AppleOrange {
	static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {
		int larryScore = evaluateScore(apples, s, t, a);
		int robScore = evaluateScore(oranges, s, t, b);
		System.out.println(larryScore);
		System.out.println(robScore);
	}

	private static int evaluateScore(int[] lancamentos, int s, int t, int pointOfReference) {
		int score = 0;
		for (int i = 0; i < lancamentos.length; i++) {
			int position = pointOfReference + lancamentos[i];
			score += evaluatePoint(s, t, position);
		}
		return score;
	}

	private static int evaluatePoint(int s, int t, int mark) {
		int score = 0;
		if (mark >= s && mark <= t) {
			score = 1;
		}
		return score;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int t = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int m = in.nextInt();
		int n = in.nextInt();
		int[] apple = new int[m];
		for (int apple_i = 0; apple_i < m; apple_i++) {
			apple[apple_i] = in.nextInt();
		}
		int[] orange = new int[n];
		for (int orange_i = 0; orange_i < n; orange_i++) {
			orange[orange_i] = in.nextInt();
		}
		countApplesAndOranges(s, t, a, b, apple, orange);
		in.close();
	}
}
