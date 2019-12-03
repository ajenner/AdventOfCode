package Day01;

import java.util.ArrayList;

public class Day1 {
    public static int question1(ArrayList<Integer> input) {
        int fuelTotal = 0;
        for (Integer i : input) {
            fuelTotal += calculate(i);
        }
        return fuelTotal;
    }

    public static int question2(ArrayList<Integer> input) {
        int fuelTotal = 0;
        int currentIterationAmount;
        for (Integer i : input) {
            currentIterationAmount = i;
            while (currentIterationAmount > 0) {
                currentIterationAmount = calculate(currentIterationAmount);
                fuelTotal += currentIterationAmount;
            }
        }
        return fuelTotal;
    }

    private static int calculate (int amount) {
        return Math.max(0, amount / 3 - 2);
    }
}
