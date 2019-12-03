package Day03;

import java.util.ArrayList;

/**
 * Created by ajenner on 03/12/2019.
 */
public class Day3 {
    private ArrayList<Wire> wires;

    public Day3(ArrayList<Wire> wires) {
        this.wires = wires;
    }

    public int question1() {
        DrawLine drawLine = new DrawLine(wires);
        drawLine.run();
        return wires.get(0).closestManhattanIntersection(wires.get(1));
    }

    public int question2() {
        return wires.get(0).closestIntersectionInSteps(wires.get(1));
    }

}
