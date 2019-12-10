package Day10;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ajenner on 10/12/2019.
 */
public class Day10 {
    ArrayList<String> input;
    ArrayList<Point> space;

    public Day10 (ArrayList<String> input) {
        this.input = input;
        initialiseSpace();
    }

    private void initialiseSpace() {
        int inputSize = input.size();
        this.space = new ArrayList<>();
        for (int x = 0; x < inputSize; x++) {
            System.out.println();
            char[] line = input.get(x).toCharArray();
            for (int y = 0; y < line.length; y++) {
                System.out.print(line[y]);
                this.space.add(new Point(y, x, line[y] == '#'));
            }
        }
    }

    public Point question1() {
        ArrayList<Point> asteroids = this.space.stream().filter(point1 -> point1.hasAsteroid).collect(Collectors.toCollection(ArrayList::new));
        for (Point point : asteroids) {
            ArrayList<Point> possibleVisibleAsteroids = asteroids.stream().filter(point1 -> !point1.equals(point)).collect(Collectors.toCollection(ArrayList::new));
            HashSet<Double> angles = new HashSet<>();
            for (Point point1 : possibleVisibleAsteroids) {
                angles.add(point.angle(point1));
            }
            point.visibleAsteroids = angles.size();
        }
        Collections.sort(asteroids, (Point p1, Point p2) -> p1.visibleAsteroids - p2.visibleAsteroids);
        return asteroids.get(asteroids.size()-1);
    }

    public Point question2() {
        Point bestPoint = question1();
        ArrayList<Point> asteroids = this.space.stream().filter(point1 -> point1.hasAsteroid && !point1.equals(bestPoint)).collect(Collectors.toCollection(ArrayList::new));
        TreeMap targets = new TreeMap<Double, ArrayList<Point>>();
        int count = 0;
        for (Point point1 : asteroids) {
            double angle = bestPoint.angle(point1);
            //When in doubt, hack it into place
            if (angle < 0) {
                angle = 180 - angle;
            } else if (angle > 0) {
                angle = 360 - angle;
            }
            if (! targets.containsKey(angle)) {
                targets.put(angle, new ArrayList<>());
            }
            ArrayList<Point> points = (ArrayList<Point>) targets.get(angle);
            points.add(point1);
            Collections.sort(points, (Point p1, Point p2) -> (int) (p1.distance(bestPoint) - p2.distance(bestPoint)));
        }

        while (count < 200) {
            for (Double key : (Set<Double>) targets.keySet()) {
                ArrayList<Point> pointsAtAngle = (ArrayList<Point>) targets.get(key);
                if (count == 199) {
                    return pointsAtAngle.get(0);
                }
                if(pointsAtAngle != null && pointsAtAngle.size() > 0) {
                    pointsAtAngle.remove(0);
                    count ++;
                }
            }
        }
        return null;
    }

    class Point {
        double x;
        double y;
        boolean hasAsteroid;
        int visibleAsteroids = 0;

        public Point(int x, int y, boolean hasAsteroid) {
            this.x = x;
            this.y = y;
            this.hasAsteroid = hasAsteroid;
        }

        public double distance(Point b) {
            return Math.hypot(this.x - b.x, this.y - b.y);
        }

        public double angle(Point b) {
            return Math.atan2(this.x - b.x, this.y - b.y);
        }

        public String toString() {
            return "X coord: " + this.x + " Y coord: " + this.y + " can see " + this.visibleAsteroids + " asteroids.";
        }
    }
}
