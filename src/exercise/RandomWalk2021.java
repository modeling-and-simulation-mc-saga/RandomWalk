package exercise;


import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
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
public class RandomWalk2021 {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        DoubleFunction<Double> invProDist = x -> {
            if (x < 0.5) {
                return -Math.sqrt(1. - 2 * x);
            }
            return Math.sqrt(2 * x - 1);
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
        String filename = RandomWalk2021.class.getSimpleName()
                + "-output-" + String.valueOf(n) + ".txt";
        try (BufferedWriter out
                = myLib.utils.FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                myLib.utils.FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }
}
