package Day02;
import java.util.ArrayList;

/**
 * Created by ajenner on Day02/12/2019.
 */
public class Day2 {

    private final ArrayList<Integer> input;
    private ArrayList<Integer> memory;
    private int programCounter;

    public Day2 (ArrayList<Integer> input) {
        this.input = input;
        this.memory = (ArrayList<Integer>) input.clone();
        this.programCounter = 0;
    }

    public int question1() {
        setNoun(12);
        setVerb(2);
        calculate();
        return this.memory.get(0);
    }

    public int question2(int target) {
        int noun = 0;
        int verb = 0;
        resetMemory();
        while (this.memory.get(0) != target) {
            if (noun < 99) {
                noun++;
            } else if (noun == 99){
                noun = 0;
                verb++;
            } else if (verb > 99) {
                System.out.print("ERROR: No matching input pair found within data provided.");
                return -1;
            }
            resetMemory();
            setNoun(noun);
            setVerb(verb);
            calculate();
        }
        return (noun * 100) + verb;
    }

    private void calculate() {
        while (programCounter != -1 && programCounter < memory.size()) {
            step();
        }
    }

    private void step() {
        switch (memory.get(programCounter)) {
            case 1:
                state1(programCounter + 1, programCounter + 2, programCounter + 3);
                programCounter += 4;
                break;
            case 2:
                state2(programCounter + 1, programCounter + 2, programCounter + 3);
                programCounter += 4;
                break;
            case 99:
                state99();
                break;
            default:
                System.out.println("Invalid state reached. Oops");
        }
    }

    private void state1(int pos1, int pos2, int pos3) {
        memory.set(memory.get(pos3), memory.get(memory.get(pos2)) + memory.get(memory.get(pos1)));
    }

    private void state2(int pos1, int pos2, int pos3) {
        memory.set(memory.get(pos3), memory.get(memory.get(pos2)) * memory.get(memory.get(pos1)));
    }

    private void state99() {
        programCounter = -1;
    }

    private void resetMemory() {
        this.programCounter = 0;
        this.memory = (ArrayList<Integer>) this.input.clone();
    }

    private void setNoun(int noun) {
        this.memory.set(1, noun);
    }

    private void setVerb(int verb) {
        this.memory.set(2, verb);
    }

}
