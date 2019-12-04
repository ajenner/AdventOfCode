package Day04;

import java.util.ArrayList;

/**
 * Created by ajenner on 04/12/2019.
 */
public class Day4 {
    private int rangeMin;
    private int rangeMax;

    public Day4(ArrayList<Integer> input) {
        this.rangeMin = input.get(0);
        this.rangeMax = input.get(1);
    }

    public int question1() {
        int counter = 0;
        for (int i = rangeMin; i < rangeMax; i++) {
            if(matchesPattern1(i)) {
                counter++;
            }
        }
        return counter;
    }

    public int question2() {
        int counter = 0;
        for (int i = rangeMin; i < rangeMax; i++) {
            if(matchesPattern2(i)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean matchesPattern1(int candidate) {
        int remainder = candidate;
        int digit = 11;
        int previous;
        boolean hasDouble = false;

        for(int i = 0; i < 6 ; i++) {
            previous = digit;
            digit = remainder % 10;
            remainder /= 10;
            if (previous < digit) {
                return false;
            }
            if (previous == digit) {
                hasDouble = true;
            }
        }
        return hasDouble;
    }

    private boolean matchesPattern2(int candidate) {
        int remainder = candidate;
        int digit = 11;
        int previous = 12;
        int previous2;
        int doubleDigit = -1;
        boolean hasDouble = false;

        for(int i = 0; i < 6 ; i++) {
            previous2 = previous;
            previous = digit;
            digit = remainder % 10;
            remainder /= 10;
            if (previous < digit) {
                return false;
            }
            if (previous == digit) {
                if (!hasDouble) {
                    doubleDigit = digit;
                }
                hasDouble = true;
            }
            if (previous2 == digit && digit == doubleDigit) {
                hasDouble = false;
            }
        }
        return hasDouble;
    }
}
