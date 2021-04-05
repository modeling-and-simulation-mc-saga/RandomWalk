package exercise;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.DoubleFunction;
import myLib.utils.FileIO;
import randomNumbers.AbstractRandom;
import randomNumbers.Transform;

/**
 *
 * @author tadaki
 */
public class ExampleRandom2021 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //Define inverse of Distribution
        DoubleFunction<Double> invProDist = x -> {
        
        
        
        };

        //変換法による乱数生成のインスタンス
        AbstractRandom aRandom = new Transform(invProDist);
        double min = -1;//下限
        double max = 1.;//上限
        int numBin = 100;//binの数
        int numSamples = 100000;//乱数の総数
        //ヒストグラムを生成
        Histogram histogram = new Histogram(min, max, numBin);
        for (int i = 0; i < numSamples; i++) {
            double x = aRandom.getNext();
//            System.out.println(x);
            histogram.put(x);
        }
        //ヒストグラムを出力
        List<Point2D.Double> plist = histogram.calculateFrequency();
        String filename = ExampleRandom2021.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }
}
