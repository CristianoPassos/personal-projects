package de.cristiano.marathon.hr;

/**
 * Find Sets Of Numbers That Add Up To 16
 */
public class SubsetsAddTo {

    int subSets(int[] set, int addTo) {
        return addsTo(set, 0, 0, addTo);
    }

    private int addsTo(int[] set, int index, int sum, int addTo) {
        if (sum == addTo) {
            return 1;
        }

        if (index >= set.length || sum > addTo) {
            return 0;
        }

        return addsTo(set, index + 1, 0, addTo)
                + addsTo(set, index + 1, sum + set[index], addTo);
    }
}
