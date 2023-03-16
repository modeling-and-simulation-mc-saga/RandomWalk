package exercise;


import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.function.DoubleFunction;
import modelExt.PositionHistogramExt;
import modelExt.SimulationExt;
import randomNumbers.AbstractRandom;
import randomNumbers.Transform;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tadaki
 */
public class RandomWalk2023 {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        DoubleFunction<Double> invProDist = x -> {
            double y;
            if (x >= .5) {
                y = Math.pow(2 * x - 1, 1. / 3.);
            } else {
                y = -Math.pow(-2 * x + 1, 1. / 3.);
            }
            return y;
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
        String filename = RandomWalk2023.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        try (PrintStream out = new PrintStream(filename)) {
            plist.forEach(p->out.println(p.x+" "+p.y));
        }
    }
}
