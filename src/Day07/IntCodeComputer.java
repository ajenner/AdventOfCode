package Day07;

import java.util.ArrayList;
/**
 * Created by ajenner on 09/12/2019.
 */
public class IntCodeComputer {
    private final ArrayList<Integer> input;
    private Integer phaseSetting;
    private Integer inputValue;
    private Integer outputValue = -1;
    private ArrayList<Integer> memory;
    private int programCounter;
    public boolean halted;
    private boolean hasOutput;

    public IntCodeComputer (ArrayList<Integer> input, Integer phaseSetting) {
        this.phaseSetting = phaseSetting;
        this.halted = false;
        this.input = input;
        this.memory = (ArrayList<Integer>) input.clone();
        this.programCounter = 0;
    }

    public void reset() {
        this.programCounter = 0;
        this.halted = false;
        this.memory = (ArrayList<Integer>) this.input.clone();
    }

    public Integer calculate(Integer phaseSetting, Integer inputValue) {
        this.phaseSetting = phaseSetting;
        this.inputValue = inputValue;
        while (!halted && programCounter < memory.size()) {
            step();
        }
        return this.outputValue;
    }

    public AmplifierDTO calculateOnGivenMemory(AmplifierDTO inputs) {
        this.inputValue = inputs.value;
        this.outputValue = inputs.value;
        this.memory = inputs.memory;
        this.hasOutput = false;
        while (!halted && !hasOutput && programCounter < memory.size()) {
            step();
        }
        return new AmplifierDTO(this.memory, this.outputValue);
    }

    private void step() {
        OPCode opCode = new OPCode();
        switch (opCode.instruction) {
            case 1:
                state1(opCode.params.get(0), opCode.params.get(1), opCode.params.get(2));
                break;
            case 2:
                state2(opCode.params.get(0), opCode.params.get(1), opCode.params.get(2));
                break;
            case 3:
                state3(opCode.params.get(0));
                break;
            case 4:
                state4(opCode.params.get(0));
                break;
            case 5:
                state5(opCode.params.get(0), opCode.params.get(1));
                break;
            case 6:
                state6(opCode.params.get(0), opCode.params.get(1));
                break;
            case 7:
                state7(opCode.params.get(0), opCode.params.get(1), opCode.params.get(2));
                break;
            case 8:
                state8(opCode.params.get(0), opCode.params.get(1), opCode.params.get(2));
                break;
            case 99:
                state99();
                break;
            default:
                System.out.println("Invalid state reached. Oops");
        }
    }

    private void state1(Parameter p1, Parameter p2, Parameter p3) {
        p3.setParam(p1.getParam() + p2.getParam());
    }

    private void state2(Parameter p1, Parameter p2, Parameter p3) {
        p3.setParam(p1.getParam() * p2.getParam());
    }

    private void state3(Parameter p1) {
        if (this.phaseSetting != -1) {
            p1.setParam(this.phaseSetting);
            this.phaseSetting = -1;
        } else {
            p1.setParam(this.inputValue);
        }
    }

    private void state4(Parameter p1) {
        int output = p1.getParam();
        this.outputValue = output;
        this.hasOutput = true;
        //System.out.println("Output from position " + p1.pointer + " is " + output);
    }

    private void state5(Parameter p1, Parameter p2) {
        if(p1.getParam() != 0) {
            programCounter = p2.getParam();
        }
    }

    private void state6(Parameter p1, Parameter p2) {
        if(p1.getParam() == 0) {
            programCounter = p2.getParam();
        }
    }

    private void state7(Parameter p1, Parameter p2, Parameter p3) {
        if(p1.getParam() < p2.getParam()) {
            p3.setParam(1);
        } else {
            p3.setParam(0);
        }
    }

    private void state8(Parameter p1, Parameter p2, Parameter p3) {
        if(p1.getParam() == p2.getParam()) {
            p3.setParam(1);
        } else {
            p3.setParam(0);
        }
    }

    private void state99() {
        halted = true;
    }

    class OPCode {
        int code;
        int instruction;
        ArrayList<Parameter> params;

        public OPCode () {
            this.code = memory.get(programCounter);
            initParams();
        }

        public void initParams() {
            params = new ArrayList<>();
            int remainder = code;
            this.instruction = code % 100;
            remainder /= 100;

            params.add(new Parameter(remainder % 10, ++programCounter));
            remainder /= 10;
            if (this.instruction != 3 && this.instruction != 4) {
                params.add(new Parameter(remainder % 10, ++programCounter));
                remainder /= 10;
                if (this.instruction != 5 && this.instruction != 6) {
                    params.add(new Parameter(remainder % 10, ++programCounter));
                }
            }
            programCounter++;
        }
    }

    class Parameter {
        int mode;
        int pointer;
        public Parameter(int mode, int pointer) {
            this.mode = mode;
            this.pointer = pointer;
        }

        public int getParam (){
            return memory.get((this.mode == 0) ? memory.get(this.pointer) : this.pointer);
        }

        public void setParam (int value) {
            memory.set(memory.get(this.pointer), value);
        }
    }
}
