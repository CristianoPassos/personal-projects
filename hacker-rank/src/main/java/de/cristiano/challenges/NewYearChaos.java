package de.cristiano.challenges;

public class NewYearChaos {
    static String minimumBribes(int[] q) {
        Integer bribes = 0;

        for (int index = q.length - 1; index >= 0; index--) {
            final int person = q[index];

            if (person - index > 3) {
                return "Too chaotic";
            }

            if (person > index) {
                bribes += person - (index + 1);
            }
        }

        return bribes.toString();
    }
}
