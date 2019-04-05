package de.cristiano.challenges;

import java.util.Arrays;
import java.util.Scanner;

public class SockMerchant {

	static int sockMerchant(int n, int[] ar) {
		int pairs = 0;
		Arrays.sort(ar);
		int temp = ar[0];
		for (int i = 1; i < ar.length; i++) {
			int j = ar[i];
			if (temp == j) {
				pairs++;
				temp = 0;
			} else {
				temp = j;
			}
		}
		return pairs;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int ar_i = 0; ar_i < n; ar_i++) {
			ar[ar_i] = in.nextInt();
		}
		int result = sockMerchant(n, ar);
		System.out.println(result);
		in.close();
	}
}
