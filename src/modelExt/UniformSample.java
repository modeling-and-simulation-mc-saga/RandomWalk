package modelExt;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import randomNumbers.AbstractRandom;
import randomNumbers.Uniform;

/**
 * 連続的な一様乱数の場合の酔歩
 * @author tadaki
 */
public class UniformSample {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AbstractRandom aRandom = new Uniform(-0.5, 0.5);//一様乱数
        int n = 100000;//walker数
        int tmax = 1000;//時間ステップ
        SimulationExt sys = new SimulationExt(aRandom, n);
        for (int t = 0; t < tmax; t++) {
            sys.oneStep();
        }
        
        List<Point2D.Double> plist
                = PositionHistogramExt.getHist(sys.getWalkers());
        String filename = UniformSample.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        try (BufferedWriter out
                = myLib.utils.FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                myLib.utils.FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }

}
