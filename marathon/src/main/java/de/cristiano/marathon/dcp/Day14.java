package de.cristiano.marathon.dcp;

/**
 * Given a array of numbers representing the stock prices of a company in chronological order,
 * write a function that calculates the maximum profit you could have made from buying and selling that stock once.
 * You must buy before you can sell it.
 * <p/>
 * For example, given [9, 11, 8, 5, 7, 10], you should return 5, since you could buy the stock at 5 dollars and sell it at 10 dollars.
 */
public class Day14 {

    int shouldHaveBoughtAt(int[] stockPrices) {
        int bestOffer = 0;
        int maxGain = Integer.MIN_VALUE;

        for (int index = 0; index < stockPrices.length; index++) {
            int gain = calculatePossibleGain(stockPrices[index], stockPrices, index + 1);
            if (gain > maxGain) {
                maxGain = gain;
                bestOffer = index;
            }
        }

        return stockPrices[bestOffer];
    }

    private int calculatePossibleGain(int stockPrice, int[] stockPrices, int index) {
        int gain = Integer.MIN_VALUE;

        while (index < stockPrices.length) {
            gain = Math.max(stockPrices[index] - stockPrice, gain);
            index++;
        }

        return gain;
    }
}
