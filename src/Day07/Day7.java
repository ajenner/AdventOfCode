package Day07;

import Day06.Day6;
import Java.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ajenner on 09/12/2019.
 */
public class Day7 {

    private List<List<Integer>> possibleInputs;
    private IntCodeComputer computer1;
    private IntCodeComputer computer2;
    private IntCodeComputer computer3;
    private IntCodeComputer computer4;
    private IntCodeComputer computer5;
    private ArrayList<Integer> input;


    public Day7 (ArrayList<Integer> input) {
        this.input = input;
    }

    public int question1() {
        this.computer1 = new IntCodeComputer(input, 0);
        int maxOutput = 0;
        Integer[] inputs = {0,1,2,3,4};
        this.possibleInputs = generatePerm(new LinkedList<Integer>(Arrays.asList(inputs)));
        for (List<Integer> permutation : possibleInputs) {
            int output = 0;
            for (int i : permutation) {
                computer1.reset();
                output = computer1.calculate(i, output);
            }
            if (output > maxOutput) {
                maxOutput = output;
            }
        }
        return maxOutput;
    }

    public int question2() {
        int maxOutput = 0;
        Integer[] inputs = {5,6,7,8,9};
        this.possibleInputs = generatePerm(new LinkedList<>(Arrays.asList(inputs)));
        for (List<Integer> permutation : possibleInputs) {
            int cycleOutput;
            AmplifierDTO ampInput1 = new AmplifierDTO((ArrayList<Integer>) this.input.clone(), 0);
            AmplifierDTO ampInput2 = new AmplifierDTO((ArrayList<Integer>) this.input.clone(), 0);
            AmplifierDTO ampInput3 = new AmplifierDTO((ArrayList<Integer>) this.input.clone(), 0);
            AmplifierDTO ampInput4 = new AmplifierDTO((ArrayList<Integer>) this.input.clone(), 0);
            AmplifierDTO ampInput5 = new AmplifierDTO((ArrayList<Integer>) this.input.clone(), 0);

            this.computer1 = new IntCodeComputer(input, permutation.get(0));
            this.computer2 = new IntCodeComputer(input, permutation.get(1));
            this.computer3 = new IntCodeComputer(input, permutation.get(2));
            this.computer4 = new IntCodeComputer(input, permutation.get(3));
            this.computer5 = new IntCodeComputer(input, permutation.get(4));

            ampInput1 = computer1.calculateOnGivenMemory(ampInput1);
            while (!computer5.halted) {
                ampInput2.value = ampInput1.value;
                ampInput2 = computer2.calculateOnGivenMemory(ampInput2);
                ampInput3.value = ampInput2.value;
                ampInput3 = computer3.calculateOnGivenMemory(ampInput3);
                ampInput4.value = ampInput3.value;
                ampInput4 = computer4.calculateOnGivenMemory(ampInput4);
                ampInput5.value = ampInput4.value;
                ampInput5 = computer5.calculateOnGivenMemory(ampInput5);
                ampInput1.value = ampInput5.value;
                ampInput1 = computer1.calculateOnGivenMemory(ampInput1);
            }
            cycleOutput = ampInput5.value;
            if (cycleOutput > maxOutput) {
                maxOutput = cycleOutput;
            }
        }
        return maxOutput;
    }

    // Shamelessly stolen from StackOverflow
    private static <E> List<List<E>> generatePerm(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
}
