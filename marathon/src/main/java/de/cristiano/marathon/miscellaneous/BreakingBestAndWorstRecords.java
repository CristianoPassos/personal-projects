package de.cristiano.marathon.miscellaneous;

import java.util.Scanner;

public class BreakingBestAndWorstRecords {
    static int[] breakingRecords(int[] score) {
        int maxScore = score[0];
        int minScore = score[0];
        int[] brokenRecords = {0, 0};

        for (int i = 0; i < score.length; i++) {
            int gameScore = score[i];
            if (gameScore > maxScore) {
                maxScore = gameScore;
                brokenRecords[0]++;
            }
            if (gameScore < minScore) {
                minScore = gameScore;
                brokenRecords[1]++;
            }
        }

        return brokenRecords;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] score = new int[n];
        for (int score_i = 0; score_i < n; score_i++) {
            score[score_i] = in.nextInt();
        }
        int[] result = breakingRecords(score);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");

        in.close();
    }
}
