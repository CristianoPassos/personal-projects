package de.cristiano.marathon.miscellaneous;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {
    static void extraLongFactorials(int n) {
        BigInteger factorial = new BigInteger("1");
        for (int i = n; i != 0; i--) {
            BigInteger temp = new BigInteger(Integer.toString(i));
            factorial = factorial.multiply(temp);
            temp = null;
        }
        System.out.println(factorial);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        extraLongFactorials(n);
        in.close();
    }
}
