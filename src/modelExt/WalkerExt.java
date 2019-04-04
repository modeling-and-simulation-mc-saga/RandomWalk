package modelExt;

import randomNumbers.AbstractRandom;

/**
 * 連続的な位置へランダムに移動するWalker
 * @author tadaki
 */
public class WalkerExt {
    protected final AbstractRandom abstractRandom;//乱数生成器
    protected double x;

    public WalkerExt(AbstractRandom abstractRandom, double x) {
        this.abstractRandom = abstractRandom;
        this.x = x;
    }
    
    public double walk(){
        double r = abstractRandom.getNext();
        x += r;
        return x;
    }

    public double getX() {
        return x;
    }
    
    
}
