package Day12;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ajenner on 12/12/2019.
 */
public class Day12 {

    Space space;

    public Day12(ArrayList<String> inputs) {
        this.space = new Space(inputs);
    }

    public int question1() {
        space.stepUntilTime(1000);
        return space.totalEnergy();
    }

    public long question2() {
        space.stepUntilRepeat();
        return LCM(space.uniqueX.size(), space.uniqueY.size(), space.uniqueZ.size());
    }

    private long LCM(long a, long b, long c) {
        return LCM(LCM(a, b), c);
    }

    private long LCM(long a, long b) {
        return (a * (b / GCD(a, b)));
    }

    private long GCD(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    class Space {
        ArrayList<Moon> moons;
        int time;
        HashSet<String> uniqueX = new HashSet();
        HashSet<String> uniqueY = new HashSet();
        HashSet<String> uniqueZ = new HashSet();

        public Space(ArrayList<String> inputs) {
            time = 0;
            initializeMoons(inputs);
        }

        private void initializeMoons(ArrayList<String> inputs) {
            moons = new ArrayList<>();
            for (String s : inputs) {
                Pattern p = Pattern.compile("-?\\d+");
                Matcher m = p.matcher(s);
                m.find();
                int x = Integer.parseInt(m.group());
                m.find();
                int y = Integer.parseInt(m.group());
                m.find();
                int z = Integer.parseInt(m.group());
                moons.add(new Moon(x, y, z));
            }
        }

        public void stepUntilTime(int target) {
            while (time != target) {
                moons.forEach(moon1 -> moons.forEach(moon2 -> moon1.calculateVelocities(moon2)));
                moons.forEach(moon -> moon.move());
                time ++;
            }
        }

        public void stepUntilRepeat() {
            boolean addX = true;
            boolean addY = true;
            boolean addZ = true;
            while(addX || addY || addZ) {
                if(addX && !uniqueX.add(currentStateX())) {
                    addX = false;
                }
                if(addY && !uniqueY.add(currentStateY())) {
                    addY = false;
                }
                if(addZ && !uniqueZ.add(currentStateZ())) {
                    addZ = false;
                }
                moons.forEach(moon1 -> moons.forEach(moon2 -> moon1.calculateVelocities(moon2)));
                moons.forEach(moon -> moon.move());
                time ++;
            }
        }

        public int totalEnergy() {
            int total = 0;
            for(Moon moon : moons){
                total += moon.kineticEnergy() * moon.potentialEnergy();
            }
            return total;
        }

        private String currentStateX() {
            StringBuilder stb = new StringBuilder();
            for (Moon moon: moons) {
                stb.append(moon.x);
                stb.append(",");
                stb.append(moon.xVel);
                stb.append(",");

            }
            return stb.toString();
        }

        private String currentStateY() {
            StringBuilder stb = new StringBuilder();
            for (Moon moon: moons) {
                stb.append(moon.y);
                stb.append(",");
                stb.append(moon.yVel);
                stb.append(",");
            }
            return stb.toString();
        }

        private String currentStateZ() {
            StringBuilder stb = new StringBuilder();
            for (Moon moon: moons) {
                stb.append(moon.z);
                stb.append(",");
                stb.append(moon.zVel);
                stb.append(",");
            }
            return stb.toString();
        }
    }

    class Moon {
        int x;
        int y;
        int z;
        int xVel;
        int yVel;
        int zVel;

        public Moon(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            xVel = 0;
            yVel = 0;
            zVel = 0;
        }

        public void move() {
            x += xVel;
            y += yVel;
            z += zVel;
        }

        public int potentialEnergy() {
            return Math.abs(x) + Math.abs(y) + Math.abs(z);
        }

        public int kineticEnergy() {
            return Math.abs(xVel) + Math.abs(yVel) + Math.abs(zVel);
        }

        public void calculateVelocities(Moon otherMoon) {
            if (x != otherMoon.x) {
                xVel += (x < otherMoon.x)? 1 : -1;
            }
            if (y != otherMoon.y) {
                yVel += (y < otherMoon.y)? 1 : -1;
            }
            if (z != otherMoon.z) {
                zVel += (z < otherMoon.z)? 1 : -1;
            }
        }

        public String toString() {
            return "<x=" + x + ", y=" + y + ", z=" + z + ">, vel=<x=" + xVel + ", y=" + yVel + ", z=" + zVel + ">";
        }
    }
}
