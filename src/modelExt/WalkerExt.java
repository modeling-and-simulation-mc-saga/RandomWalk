package modelExt;

import randomNumbers.AbstractRandom;

/**
 * Continuous random walkersr
 * @author tadaki
 */
public class WalkerExt {
    protected final AbstractRandom abstractRandom;
    protected double x;

    /**
     * @param abstractRandom
     * @param x 
     */
    public WalkerExt(AbstractRandom abstractRandom, double x) {
        this.abstractRandom = abstractRandom;
        this.x = x;
    }
    
    public double walk(){
        x += abstractRandom.getNext();
        return x;
    }

    public double getX() {
        return x;
    }
    
    
}
