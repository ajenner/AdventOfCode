package Day11;

/**
 * Created by ajenner on 03/12/2019.
 */
public class Point {
    int x;
    int y;
    int colour;
    boolean visited;

    public Point(int x, int y, int colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.visited = false;
    }
}
