package de.cristiano.marathon.miscellaneous;

class Comparator {
	boolean compare(int a, int b) {
		return a == b;
	}

	boolean compare(String a, String b) {
		if (a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}

	boolean compare(int[] a, int[] b) {
		if (a == null || b == null) {
			return false;
		}
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < b.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}

		return true;
	}
}
