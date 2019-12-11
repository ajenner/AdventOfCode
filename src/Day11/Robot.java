package Day11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ajenner on 11/12/2019.
 */
public class Robot {
    ArrayList<Point> grid;
    int direction;
    int visitedLocations;
    IntCodeComputer brain;
    Point currentPos;
    int gridDimension = 100;


    public Robot(LinkedList<BigInteger> input) {
        initialiseGrid();
        direction = 0;
        visitedLocations = 0;
        brain = new IntCodeComputer(input);
    }

    private void initialiseGrid() {
        grid = new ArrayList<>();
        for (int i = 0; i < gridDimension; i++) {
            for (int j = 0; j < gridDimension; j++) {
                grid.add(new Point(i,j,0));
            }
        }
        currentPos = grid.get((grid.size() / 2) + (gridDimension / 2));
    }

    public int moveAndPaint(int question) {
        currentPos.colour = question - 1;
        while(!brain.halted) {
            if (!currentPos.visited) {
                visitedLocations++;
            }
            currentPos.visited = true;
            brain.calculate(currentPos.colour);
            currentPos.colour = brain.colour;
            rotate(brain.direction);
            move();
        }
        if(question == 2) {
            createImageFileFromGrid();
        }
        return visitedLocations;
    }

    private void rotate(int rotation) {
        if (rotation == 0) {
            if(direction == 0) {
                direction = 3;
            } else {
                direction -= 1;
            }
        } else {
            if(direction == 3) {
                direction = 0;
            } else {
                direction += 1;
            }
        }
    }

    private void move() {
        int currentIndex = grid.indexOf(currentPos);
        switch (direction) {
            // left (x-1)
            case 0:
                currentIndex -= gridDimension;
                break;
            // Up (y+1)
            case 1:
                currentIndex += 1;
                break;
            // Right (x+1)
            case 2:
                currentIndex += gridDimension;
                break;
            // Down (y-1)
            case 3:
                currentIndex -= 1;
                break;
            default:
                System.out.print("Why?");
        }
        currentPos = grid.get(currentIndex);
    }

    public void createImageFileFromGrid() {
        BufferedImage image = new BufferedImage(gridDimension, gridDimension, BufferedImage.TYPE_BYTE_BINARY);

        for(int row = 0; row < this.gridDimension; row++) {
            for (int column = 0; column < this.gridDimension; column++) {
                image.setRGB(column, row, this.getPixelRGBValue(grid.get(row * gridDimension + column).colour));
            }
        }
        try {
            ImageIO.write(image, "jpg", new File("C:\\AdventOfCode\\src\\Data\\day11OutputImage.jpg"));
            System.out.println("Image Created");
        }
        catch (Exception e) {
            System.out.println("Why have you done this?");
        }
    }

    private int getPixelRGBValue(int value) {
        if (value == 0) {
            return Color.BLACK.getRGB();
        }
        else if (value == 1) {
            return Color.WHITE.getRGB();
        }
        else {
            return Color.TRANSLUCENT;
        }
    }
}
