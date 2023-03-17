package modelExt;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Histbram for continuous random walkers
 *
 * @author tadaki
 */
public class PositionHistogramExt {

    private PositionHistogramExt() {
    }

    /**
    /**
     * Getting histgram of positions
     *
     * @param walkers target walkers
     * @return 
     */
    public static List<Point2D.Double> getHist(List<WalkerExt> walkers) {
        return getHist(walkers,1.);
    }
    public static List<Point2D.Double> getHist(List<WalkerExt> walkers,double bin) {
        //observing the range
        double xmaxd = (int) Math.ceil(walkers.get(0).getX());
        double xmind = xmaxd;
        for (WalkerExt w : walkers) {
            xmaxd = Math.max(xmaxd, w.getX());
            xmind = Math.min(xmind, w.getX());
        }
        int xmax = (int) Math.ceil(xmaxd/bin)+1;
        int xmin = (int) Math.floor(xmind/bin)-1;

        int h[] = new int[xmax - xmin + 1];
        walkers.stream().map(w -> (int) Math.floor(w.getX()/bin - xmin))
                .forEachOrdered(k -> h[k]++);
        //converting histogram into list
        List<Point2D.Double> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < h.length; i++) {
            double x = (i + xmin + 0.5)*bin;
            double y = (double) h[i] / walkers.size();
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

}
