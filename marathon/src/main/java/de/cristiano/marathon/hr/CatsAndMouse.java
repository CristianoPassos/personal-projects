package de.cristiano.marathon.hr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CatsAndMouse {
	static String catAndMouse(int x, int y, int z) {
		int distanceCatA = Math.abs(x - z);
		int distanceCatB = Math.abs(y - z);

		if (distanceCatA == distanceCatB) {
			return "Mouse C";
		} else if (distanceCatA < distanceCatB) {
			return "Cat A";
		} else {
			return "Cat B";
		}
	}

	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(scan.nextLine().trim());

		for (int qItr = 0; qItr < q; qItr++) {
			String[] xyz = scan.nextLine().split(" ");

			int x = Integer.parseInt(xyz[0].trim());

			int y = Integer.parseInt(xyz[1].trim());

			int z = Integer.parseInt(xyz[2].trim());

			String result = catAndMouse(x, y, z);

			bw.write(result);
			bw.newLine();
		}

		bw.close();
	}
}
