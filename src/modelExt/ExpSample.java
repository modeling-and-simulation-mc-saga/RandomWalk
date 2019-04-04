package modelExt;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import randomNumbers.AbstractRandom;
import randomNumbers.Transform;

/**
 * 指数関数分布の場合の酔歩
 * @author tadaki
 */
public class ExpSample {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //指数分布に対応した分布関数の逆関数を定義
        // A * exp (-x)
        double A = Math.E / (Math.E - 1);
        Function<Double, Double> invProDist = (x) -> {
            return -Math.log(1 - x / A);
        };
        //変換法による乱数生成のインスタンス
        AbstractRandom aRandom = new Transform(invProDist);
        int n = 100000;
        int tmax = 1000;
        SimulationExt sys = new SimulationExt(aRandom, n);
        for (int t = 0; t < tmax; t++) {
            sys.oneStep();
        }
        List<Point2D.Double> plist
                = PositionHistogramExt.getHist(sys.getWalkers());
        String filename = ExpSample.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        try (BufferedWriter out
                = myLib.utils.FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                myLib.utils.FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }

}
