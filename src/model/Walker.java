package model;

/**
 * Walker class
 *
 * @author tadaki
 */
public class Walker {

    protected int x;//position of the walker
    protected final double p;//probability for moving right

    /**
     * @param x initial position
     */
    public Walker(int x) {
        this(x, 0.5);//probability for moving right is .5
    }

    /**
     * @param x initial position
     * @param p probability for moving right
     */
    public Walker(int x, double p) {
        this.x = x;
        this.p = p;
    }

    /**
     * one step
     *
     * @return new position
     */
    public int walk() {
        double r = Math.random();
        if (r < p) {//x++ with probability p
            x++;
        } else {//x-- with probability 1-p
            x--;
        }
        return x;
    }

    /**
     * return current position
     *
     * @return
     */
    public int getX() {
        return x;
    }
}
