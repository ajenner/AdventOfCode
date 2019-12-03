package Day03;

/**
 * Created by ajenner on 03/12/2019.
 */
public class LineSegment {
    Point p1;
    Point p2;
    int steps;
    public LineSegment (Point p1, Point p2, int steps) {
        this.p1 = p1;
        this.p2 = p2;
        this.steps = steps;
    }

    public Point intersection (LineSegment line2) {
        double a1 = this.p2.y - this.p1.y;
        double b1 = this.p1.x - this.p2.x;
        double c1 = a1 * this.p1.x + b1 * this.p1.y;

        double a2 = line2.p2.y - line2.p1.y;
        double b2 = line2.p1.x - line2.p2.x;
        double c2 = a2 * line2.p1.x + b2 * line2.p1.y;

        double delta = a1 * b2 - a2 * b1;
        if (delta == 0) {
            return null;
        }
        Point intersection = new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta, 0);

        if(!onSegment(this.p1, intersection, this.p2) || !onSegment(line2.p1, intersection, line2.p2)) {
            return null;
        }
        intersection.steps = this.steps - this.stepsFromGivenPointToEnd(intersection) + line2.steps - line2.stepsFromGivenPointToEnd(intersection);
        return intersection;
    }

    private boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    private int stepsFromGivenPointToEnd(Point q) {
        if (q.x == p2.x) {
            return Math.abs((int)(p2.y - q.y));
        } else {
            return Math.abs((int)(p2.x - q.x));
        }
    }
}
