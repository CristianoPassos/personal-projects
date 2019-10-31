package de.cristiano.marathon;

public class JumpingClouds {

    public static int jumpingOnClouds(int[] clouds) {
        int thunderCloud = 1;
        int jumps = 0;
        int index = 0;
        int indexLength = clouds.length - 1;

        while (index < indexLength) {
            int cloudPlusTwo = index + 2;
            if (cloudPlusTwo < indexLength && clouds[cloudPlusTwo] == thunderCloud) {
                index++;
            } else {
                index += 2;
            }
            jumps++;
        }

        return jumps;
    }
}
