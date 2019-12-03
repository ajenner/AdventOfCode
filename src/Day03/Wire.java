package Day03;

import java.util.ArrayList;

/**
 * Created by ajenner on 03/12/2019.
 */
public class Wire {
    private ArrayList<String> directions;
    private ArrayList<Integer> impulses;
    public ArrayList<LineSegment> segments = new ArrayList<>();

    public Wire (String[] Dimensions) {
        ArrayList<String> directions = new ArrayList<>();
        ArrayList<Integer> impulses = new ArrayList<>();
        for (String s : Dimensions) {
            String[] part = s.split("(?<=\\D)");
            directions.add(part[0]);
            impulses.add(Integer.parseInt(part[1]));
        }
        this.directions = directions;
        this.impulses = impulses;
        generateLineSegments();
    }

    private void generateLineSegments() {
        int currX = 0;
        int currY = 0;
        int steps = 0;
        int distance;

        for (int i = 0; i < directions.size(); i++) {
            Point p1 = new Point(currX, currY, 0);
            Point p2;
            distance = impulses.get(i);
            steps += distance;
            switch (directions.get(i)){
                case "U":
                    currY += distance;
                    p2 = new Point(currX, currY, 0);
                    this.segments.add(new LineSegment(p1, p2, steps));
                    break;
                case "R":
                    currX += distance;
                    p2 = new Point(currX, currY, 0);
                    this.segments.add(new LineSegment(p1, p2, steps));
                    break;
                case "D":
                    currY -= distance;
                    p2 = new Point(currX, currY, 0);
                    this.segments.add(new LineSegment(p1, p2, steps));
                    break;
                case "L":
                    currX -= distance;
                    p2 = new Point(currX, currY, 0);
                    this.segments.add(new LineSegment(p1, p2, steps));
                    break;
                default:
                    System.out.print("Why have you done this");
            }
        }
    }

    public ArrayList<Point> intersections (Wire wire2) {
        ArrayList<Point> results = new ArrayList<>();
        for (LineSegment line : this.segments) {
            for (LineSegment line2 : wire2.segments) {
                Point intersection = line.intersection(line2);
                if(intersection != null) {
                    results.add(intersection);
                }
            }
        }
        return results;
    }

    private double manhattanDistance(Point p1, Point p2) {
        return Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);
    }

    public int closestManhattanIntersection(Wire wire2) {
        int closest = Integer.MAX_VALUE;
        for (Point p : intersections(wire2)) {
            int distance =  (int) manhattanDistance(new Point(0,0,0), p);
            if (distance < closest && (p.x != 0 || p.y != 0)) {
                closest = distance;
            }
        }
        return closest;
    }

    public int closestIntersectionInSteps(Wire wire2) {
        int closest = Integer.MAX_VALUE;
        for (Point p : intersections(wire2)) {
            int distance =  p.steps;
            if (distance < closest) {
                closest = distance;
            }
        }
        return closest;
    }
}
