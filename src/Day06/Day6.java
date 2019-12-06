package Day06;

import Day05.Day5;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ajenner on 06/12/2019.
 */
public class Day6 {

    HashMap<String, Orbital> nodes = new HashMap<>();

    public Day6 (ArrayList<String> inputs) {
        for(String s : inputs) {
            String[] split = s.split("\\)");
            Orbital parent = nodes.get(split[0]);
            if (parent == null) {
                parent = new Orbital(split[0]);
                nodes.put(split[0], parent);
            }
            Orbital child = nodes.get(split[1]);
            if (child == null) {
                child = new Orbital(split[1]);
                nodes.put(split[1], child);
            }
            child.orbits = parent;
        }
    }

    public int question1() {
        int totalOrbits = 0;
        for (Orbital orbital : nodes.values()) {
            totalOrbits += orbital.indirectOrbits(0);
        }
        return totalOrbits;
    }

    public int question2() {
        Orbital you = nodes.get("YOU");
        Orbital santa = nodes.get("SAN");

        String lca = lowestCommonAncestor(you.pathToRoot(null), santa.pathToRoot(null));
        return you.traversalsToNode(lca, 0) + santa.traversalsToNode(lca, 0);
    }

    private String lowestCommonAncestor (ArrayList<String> path1, ArrayList<String> path2) {
        ArrayList<String> intersection = new ArrayList<>(path1);
        intersection.retainAll(path2);
        return intersection.get(0);
    }

    class Orbital {
        String key;
        Orbital orbits;

        Orbital (String key) {
            this.key = key;
        }

        int indirectOrbits(int count) {
            if (this.orbits == null) {
                return count;
            } else {
                return this.orbits.indirectOrbits(count + 1);
            }
        }

        int traversalsToNode(String key, int count) {
            if (this.orbits.key == key)  {
                return count;
            } else if (this.orbits == null) {
                return -1;
            } else {
                return this.orbits.traversalsToNode(key, count + 1);
            }
        }

        ArrayList<String> pathToRoot (ArrayList<String> path) {
            if (path == null) {
                path = new ArrayList<>();
            }
            path.add(this.key);
            if(this.orbits == null) {
                return path;
            } else {
                return this.orbits.pathToRoot(path);
            }
        }
    }
}
