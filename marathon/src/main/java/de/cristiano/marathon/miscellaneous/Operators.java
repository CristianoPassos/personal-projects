package de.cristiano.marathon.miscellaneous;

import java.util.Scanner;

public class Operators {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double meal_cost = in.nextDouble();
        int tip_percent = in.nextInt();
        int tax_percent = in.nextInt();
        in.close();

        double tipValue = meal_cost * (tip_percent / 100d);
        double taxValue = meal_cost * (tax_percent / 100d);
        double realMealCost = meal_cost + tipValue + taxValue;

        System.out.println("The total meal cost is " + Math.round(realMealCost) + " dollars.");
    }
}
