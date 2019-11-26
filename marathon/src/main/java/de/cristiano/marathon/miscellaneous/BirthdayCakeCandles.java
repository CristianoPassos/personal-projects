package de.cristiano.marathon.miscellaneous;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BirthdayCakeCandles {

    private static Map<Integer, Integer> candles = new HashMap<>();

    static int birthdayCakeCandles(int n, int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            int candleSize = ar[i];
            addCandle(candleSize);
        }
        return getBiggerCandleQuantiy();
    }

    private static int getBiggerCandleQuantiy() {
        int biggerCandle = Collections.max(candles.keySet());
        return candles.get(biggerCandle);
    }

    private static void addCandle(int candleSize) {
        if (candles.containsKey(candleSize)) {
            int amount = candles.get(candleSize);
            amount += 1;
            candles.put(candleSize, amount);
        } else {
            candles.put(candleSize, 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int ar_i = 0; ar_i < n; ar_i++) {
            ar[ar_i] = in.nextInt();
        }
        int result = birthdayCakeCandles(n, ar);
        System.out.println(result);
        in.close();
    }
}
