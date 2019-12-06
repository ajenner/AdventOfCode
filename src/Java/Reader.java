package Java;

import Day03.Wire;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ajenner on Day02/12/2019.
 */
public class Reader {
    private final File file;

    public Reader (String fileName) {
        this.file = new File(fileName);
    }

    public ArrayList<Integer> readAsIntegers() {
        ArrayList<Integer> inputList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                inputList.add(scanner.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public ArrayList<Integer> readAsIntegers(String splitPattern) {
        ArrayList<Integer> inputList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            String[] input = scanner.nextLine().split(splitPattern);
            for (String s : input) {
                inputList.add(Integer.parseInt(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public ArrayList<String> readAsStrings() {
        ArrayList<String> inputList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                inputList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public ArrayList<Wire> readEachWire() {
        ArrayList<Wire> inputList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                inputList.add(new Wire(scanner.nextLine().split(",")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public static int readInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter input: ");
            return scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
