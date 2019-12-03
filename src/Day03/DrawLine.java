package Day03;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawLine extends JComponent {
    private ArrayList<Wire> wires;

    public DrawLine(ArrayList<Wire> wires) {
        this.wires = wires;
    }

    @Override
    public void paint(Graphics g) {
        // Draw a simple line using the Graphics2D draw() method.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.RED);
        double scale = 40;
        int adjustment = 500;
        for (LineSegment line : wires.get(0).segments) {
            g2.draw(new Line2D.Double(adjustment + (line.p1.x / scale), adjustment + (line.p1.y / scale),
                    adjustment + (line.p2.x / scale), adjustment + (line.p2.y / scale)));
        }
        g2.setColor(Color.GREEN);
        for (LineSegment line : wires.get(1).segments) {
            g2.draw(new Line2D.Double(adjustment + (line.p1.x / scale), adjustment + (line.p1.y / scale),
                    adjustment + (line.p2.x / scale), adjustment + (line.p2.y / scale)));
        }
        g2.setColor(Color.BLACK);
        g2.fillOval(495,495,10,10);

        for(Point point : wires.get(0).intersections(wires.get(1))) {
            g2.fillOval((int)(point.x/scale -5 + adjustment), (int)(point.y/scale - 5 +adjustment), 10, 10);
        }

    }

    public void run() {
        JFrame frame = new JFrame("Draw Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawLine(this.wires));
        frame.pack();
        frame.setSize(new Dimension(1000, 1000));
        frame.setVisible(true);
    }
}