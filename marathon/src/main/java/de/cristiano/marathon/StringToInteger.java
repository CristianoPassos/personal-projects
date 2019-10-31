package de.cristiano.marathon;

import java.util.Scanner;

public class StringToInteger {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String S = in.next();
		try {
			Integer value = Integer.parseInt(S);
			System.out.print(value);
		} catch (NumberFormatException e) {
			System.out.print("Bad String");
		}
		in.close();
	}
}
