package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simulation of one dimensional simple random walk
 *
 * @author tadaki
 */
public class Simulation {

    private final List<Walker> walkers;//list of walkers
    private double p;//probability of moving right
    private int n;//the number of walkers

    /**
     * @param n the number of walkers
     */
    public Simulation(int n) {
        this(n, 0.5);
    }

    /**
     * @param n the number of walkers
     * @param p probability of moving right
     */
    public Simulation(int n, double p) {
        walkers = Collections.synchronizedList(new ArrayList<>());
        this.p = p;
        this.n = n;
        //initializing walkers
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, p));
        }
    }

    /**
     * Clear all walkers and create new list
     */
    public void initialize() {
        walkers.clear();
        for (int i = 0; i < n; i++) {
            walkers.add(new Walker(0, p));
        }
    }

    /**
     * update one step
     *
     * @return list of walker's position
     */
    public List<Integer> oneStep() {
        List<Integer> pList = Collections.synchronizedList(new ArrayList<>());
        //update all walkers and store positions of new walker's positions
        walkers.stream().map(w -> w.walk()).forEachOrdered(x -> pList.add(x));
        return pList;
    }

    public List<Walker> getWalkers() {
        return walkers;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setN(int n) {
        this.n = n;
    }

}
