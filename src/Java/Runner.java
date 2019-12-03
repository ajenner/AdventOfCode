package Java;

import Day01.Day1;
import Day02.Day2;

import java.util.ArrayList;

/**
 * Created by ajenner on Day02/12/2019.
 */
public class Runner {
    public static void main(String[] args) {
        runDay1();
        runDay2();
    }

    private static void runDay1() {
        System.out.println("Day1: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d1.txt");
        ArrayList<Integer> day1Inputs = reader.readAsIntegers();
        System.out.println("Question 1: " + Day1.question1(day1Inputs) + "\nQuestion 2: " + Day1.question2(day1Inputs));
    }

    private static void runDay2() {
        System.out.println("Day2: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d2.txt");
        ArrayList<Integer> day2Inputs = reader.readAsOpcodes();
        Day2 day2 = new Day2(day2Inputs);
        System.out.println("Question 1: " + day2.question1() + "\nQuestion 2: " + day2.question2(19690720));
    }
}
