package br.com.cristiano.easy;

import java.util.Scanner;

public class PickingNumbers {
	static int pickingNumbers(int[] a) {
		return 0;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int result = pickingNumbers(a);
		System.out.println(result);
		in.close();
	}

}