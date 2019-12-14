package de.cristiano.marathon.miscellaneous;

import java.util.HashSet;
import java.util.Set;

public class OddNumbers {
	static int[] oddNumbers(int l, int r) {
		Set<Integer> oddNumbers = new HashSet<Integer>();
		for (int i = l; i <= r; i++) {
			if (i % 2 != 0) {
				oddNumbers.add(i);
			}
		}
		return convertToArray(oddNumbers);
	}

	private static int[] convertToArray(Set<Integer> oddNumbers) {
		if (oddNumbers == null | oddNumbers.isEmpty()) {
			return new int[1];
		}
		int[] array = new int[oddNumbers.size()];
		int i = 0;
		for (int number : oddNumbers) {
			array[i++] = number;
		}
		return array;
	}

}
