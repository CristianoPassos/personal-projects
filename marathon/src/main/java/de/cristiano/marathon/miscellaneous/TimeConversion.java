package de.cristiano.marathon.miscellaneous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TimeConversion {
	static String timeConversion(String s) {
		SimpleDateFormat oldFormat = new SimpleDateFormat("hh:mm:ssaa");
		SimpleDateFormat newFormat = new SimpleDateFormat("HH:mm:ss");
		String conversion = null;
		try {
			conversion = newFormat.format(oldFormat.parse(s));
		} catch (ParseException e) {
			System.out.println("Not possible to convert:" + s);
		}
		return conversion;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String result = timeConversion(s);
		System.out.println(result);
		in.close();
	}

}
