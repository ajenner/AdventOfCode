package Day11;

import Java.Reader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ajenner on 09/12/2019.
 */
public class IntCodeComputer {
    private LinkedList<BigInteger> memory;
    private int programCounter;
    public boolean halted;
    private int relativeBase = 0;
    public int colour = -1;
    public int direction = -1;
    private boolean hasOutput;
    private int inputValue;


    public IntCodeComputer(LinkedList<BigInteger> input) {
        this.halted = false;
        this.memory = (LinkedList<BigInteger>) input.clone();
        for(long i = memory.size(); i < 100000; i++) {
            memory.add(new BigInteger("0"));
        }
        this.programCounter = 0;
    }

    public void calculate(int inputValue) {
        this.inputValue = inputValue;
        this.colour = -1;
        this.direction = -1;
        this.hasOutput = false;
        while (!halted && !hasOutput) {
            step();
        }
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
            case 9:
                state9(opCode.params.get(0));
                break;
            case 99:
                state99();
                break;
            default:
                System.out.println("Invalid state reached. Oops");
        }
    }

    private void state1(Parameter p1, Parameter p2, Parameter p3) {
        p3.setParam(p1.getParam().add(p2.getParam()));
    }

    private void state2(Parameter p1, Parameter p2, Parameter p3) {
        p3.setParam(p1.getParam().multiply(p2.getParam()));
    }

    private void state3(Parameter p1) {
        p1.setParam(BigInteger.valueOf(this.inputValue));
    }

    private void state4(Parameter p1) {
        if(this.colour == -1) {
            this.colour = p1.getParam().intValue();
        } else if (this.direction == -1) {
            this.direction = p1.getParam().intValue();
            this.hasOutput = true;
        }
    }

    private void state5(Parameter p1, Parameter p2) {
        if(!p1.getParam().equals(BigInteger.valueOf(0))) {
            programCounter = p2.getParam().intValue();
        }
    }

    private void state6(Parameter p1, Parameter p2) {
        if(p1.getParam().equals(BigInteger.valueOf(0))) {
            programCounter = p2.getParam().intValue();
        }
    }

    private void state7(Parameter p1, Parameter p2, Parameter p3) {
        if(p1.getParam().compareTo(p2.getParam()) == -1) {
            p3.setParam(BigInteger.valueOf(1));
        } else {
            p3.setParam(BigInteger.valueOf(0));
        }
    }

    private void state8(Parameter p1, Parameter p2, Parameter p3) {
        if(p1.getParam().equals(p2.getParam())) {
            p3.setParam(BigInteger.valueOf(1));
        } else {
            p3.setParam(BigInteger.valueOf(0));
        }
    }

    private void state9(Parameter p1) {
        relativeBase += p1.getParam().intValue();
    }

    private void state99() {
        halted = true;
    }

    class OPCode {
        int code;
        int instruction;
        ArrayList<Parameter> params;

        public OPCode () {
            this.code = memory.get(programCounter).intValue();
            initParams();
        }

        public void initParams() {
            params = new ArrayList<>();
            int remainder = code;
            this.instruction = code % 100;
            remainder /= 100;

            params.add(new Parameter(remainder % 10, ++programCounter));
            remainder /= 10;
            if (this.instruction != 3 && this.instruction != 4 && this.instruction != 9) {
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

        public BigInteger getParam () {
            switch (this.mode) {
                case 0:
                    return memory.get(memory.get(this.pointer).intValue());
                case 1:
                    return memory.get(this.pointer);
                case 2:
                    return memory.get(memory.get(this.pointer).intValue() + relativeBase);
                default:
                    System.out.println("How have you done this?");
                    return BigInteger.valueOf(-1);
            }
        }

        public void setParam (BigInteger value) {
            switch (this.mode) {
                case 0:
                    memory.set(memory.get(this.pointer).intValue(), value);
                    break;
                case 2:
                    memory.set(memory.get(this.pointer).intValue() + relativeBase, value);
                    break;
                default:
                    System.out.println("How have you done this?");
            }
        }
    }
}
