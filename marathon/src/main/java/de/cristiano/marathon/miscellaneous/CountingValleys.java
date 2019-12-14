package de.cristiano.marathon.miscellaneous;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CountingValleys {
    private static final String DOWN_HILL = "D";

    private static final String UP_HILL = "U";

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the countingValleys function below.
    public static int countingValleys(int countSteps, String path) {
        final String[] steps = path.split("");
        int level = 0;
        int valleys = 0;

        for (String step : steps) {
            if (step.equalsIgnoreCase(UP_HILL)) {
                level++;
            } else if (step.equalsIgnoreCase(DOWN_HILL)) {
                if (level == 0) {
                    valleys++;
                }
                level--;
            }
        }

        return valleys;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
