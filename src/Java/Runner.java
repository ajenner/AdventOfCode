package Java;

import Day01.Day1;
import Day02.Day2;
import Day03.Day3;
import Day03.Wire;
import Day04.Day4;
import Day05.Day5;
import Day06.Day6;
import Day07.Day7;
import Day09.IntCodeComputer;
import Day08.Day8;
import Day10.Day10;
import Day12.Day12;
import Day11.Robot;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ajenner on Day02/12/2019.
 */
public class Runner {
    public static void main(String[] args) {
//        runDay1();
//        runDay2();
//        runDay3();
//        runDay4();
//        runDay5();
//        runDay6();
//        runDay7();
//        runDay8();
//        runDay9();
//          runDay10();
        //runDay11();
        runDay12();
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
        ArrayList<Integer> day2Inputs = reader.readAsIntegers(",");
        Day2 day2 = new Day2(day2Inputs);
        System.out.println("Question 1: " + day2.question1() + "\nQuestion 2: " + day2.question2(19690720));
    }

    private static void runDay3() {
        System.out.println("Day3: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d3.txt");
        ArrayList<Wire> day3Inputs = reader.readEachWire();
        Day3 day3 = new Day3(day3Inputs);
        System.out.println("Question 1: " + day3.question1() + "\nQuestion 2: " + day3.question2());
    }

    private static void runDay4() {
        System.out.println("Day4: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d4.txt");
        ArrayList<Integer> day4Inputs = reader.readAsIntegers("-");
        Day4 day4 = new Day4(day4Inputs);
        System.out.println("Question 1: " + day4.question1() + "\nQuestion 2: " + day4.question2());
    }

    private static void runDay5() {
        System.out.println("Day5: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d5.txt");
        ArrayList<Integer> day5Inputs = reader.readAsIntegers(",");
        Day5 day5 = new Day5(day5Inputs);
        day5.calculate();
    }

    private static void runDay6() {
        System.out.println("Day6: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d6.txt");
        ArrayList<String> day6Inputs = reader.readAsStrings();
        Day6 day6 = new Day6(day6Inputs);
        System.out.println("Question 1: " + day6.question1() + "\nQuestion 2: " + day6.question2());
    }

    private static void runDay7() {
        System.out.println("Day7: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d7.txt");
        ArrayList<Integer> day7Inputs = reader.readAsIntegers(",");
        Day7 day7 = new Day7(day7Inputs);
        System.out.println("Question 1: " + day7.question1() +  "\nQuestion 2: " + day7.question2());
    }

    private static void runDay8() {
        System.out.println("Day8: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d8.txt");
        ArrayList<String> day8Inputs = reader.readAsStrings();
        Day8 day8 = new Day8(day8Inputs.get(0));
        System.out.println("Question 1: " + day8.question1(25, 6) + "\nQuestion 2: " + day8.question2(25, 6));
    }

    private static void runDay9() {
        System.out.println("Day9: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d9.txt");
        LinkedList<BigInteger> day9Inputs = reader.readAsLinkedBigIntegers(",");
        IntCodeComputer day9 = new IntCodeComputer(day9Inputs);
        day9.calculate();
    }

    private static void runDay10() {
        System.out.println("Day10: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d10.txt");
        ArrayList<String> day10Inputs = reader.readAsStrings();
        Day10 day10 = new Day10(day10Inputs);
        System.out.println("Question 1: " + day10.question1() + "\nQuestion 2: " + day10.question2());
    }

    private static void runDay11() {
        System.out.println("Day11: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d11.txt");
        LinkedList<BigInteger> day11Inputs = reader.readAsLinkedBigIntegers(",");
        Robot day11 = new Robot(day11Inputs);
        System.out.println("Question 1: " + day11.moveAndPaint(1));
        day11 = new Robot(day11Inputs);
        System.out.println("\nQuestion 2: " + day11.moveAndPaint(2));
    }

    private static void runDay12() {
        System.out.println("Day12: ");
        Reader reader = new Reader("C:\\AdventOfCode\\src\\Data\\d12.txt");
        ArrayList<String> day12Inputs = reader.readAsStrings();
        Day12 day12 = new Day12(day12Inputs);
        System.out.println("Question 1: " + day12.question1());
        day12 = new Day12(day12Inputs);
        System.out.println("Question 2: " + day12.question2());
    }
}
