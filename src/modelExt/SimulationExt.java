package modelExt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import randomNumbers.AbstractRandom;

/**
 * Simulation of one dimensional continuous random walk
 *
 * @author tadaki
 */
public class SimulationExt {

    private final List<WalkerExt> walkers;//list of walkers
    private final AbstractRandom abstractRandom;
    private int n;//the number of walkers

    /**
     *
     * @param abstractRandom
     * @param n the number of walkers
     */
    public SimulationExt(AbstractRandom abstractRandom, int n) {
        walkers = Collections.synchronizedList(new ArrayList<>());
        this.abstractRandom = abstractRandom;
        this.n = n;
        //initializing walkers
        for (int i = 0; i < n; i++) {
            walkers.add(new WalkerExt(abstractRandom, 0.));
        }
    }

    /**
     * Clear all walkers and create new list
     */
    public void initialize() {
        walkers.clear();
        for (int i = 0; i < n; i++) {
            walkers.add(new WalkerExt(abstractRandom, 0.));
        }
    }

    /**
     * update one step
     *
     * @return list of walker's position
     */
    public List<Double> oneStep() {
        List<Double> pList = Collections.synchronizedList(new ArrayList<>());
        //update all walkers and store positions of new walker's positions
        walkers.stream().map(w -> w.walk()).forEachOrdered(x -> pList.add(x));
        return pList;
    }

    public List<WalkerExt> getWalkers() {
        return walkers;
    }

    public void setN(int n) {
        this.n = n;
    }

}
