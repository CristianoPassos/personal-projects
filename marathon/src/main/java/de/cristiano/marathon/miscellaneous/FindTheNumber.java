package de.cristiano.marathon.miscellaneous;

public class FindTheNumber {

	public static String findNumber(int[] arr, int k) {
		for (int i : arr) {
			if (i == k) {
				return "YES";
			}
		}
		return "NO";
	}
}
