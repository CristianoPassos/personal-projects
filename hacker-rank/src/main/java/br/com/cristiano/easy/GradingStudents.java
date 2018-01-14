package br.com.cristiano.easy;

import java.util.Scanner;

public class GradingStudents {
	static int[] nextFives = new int[] { 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100 };

	static int[] solve(int[] grades) {
		int[] gradesRounded = new int[grades.length];
		for (int i = 0; i < grades.length; i++) {
			int grade = grades[i];
			if (grade >= 38) {
				int nextMultiple = findNextMultipleOfFive(grade);
				if (nextMultiple - grade < 3) {
					grade = nextMultiple;
				}
			}
			gradesRounded[i] = grade;
		}
		return gradesRounded;
	}

	private static int findNextMultipleOfFive(int grade) {
		int nextMultiple = grade;
		for (int i = 0; i < nextFives.length; i++) {
			if (nextFives[i] >= grade) {
				nextMultiple = nextFives[i];
				break;
			}
		}
		return nextMultiple;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] grades = new int[n];
		for (int grades_i = 0; grades_i < n; grades_i++) {
			grades[grades_i] = in.nextInt();
		}
		int[] result = solve(grades);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
		}
		System.out.println("");
		in.close();
	}
}
