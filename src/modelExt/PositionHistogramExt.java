package modelExt;

import model.*;
import java.awt.geom.Point2D;
import java.util.List;
import myLib.utils.Utils;

/**
 * Random Walkにおいて、各位置のWalkerの頻度を計測
 *
 * @author tadaki
 */
public class PositionHistogramExt {

    /**
     * このクラスのインスタンスは生成できない
     */
    private PositionHistogramExt() {
    }

    /**
     * 位置ヒストグラムの取得
     *
     * @param walkers 対象となるWalkerのリスト
     * @return 位置とその位置に居るWalkerの比率のリスト
     */
    public static List<Point2D.Double> getHist(List<WalkerExt> walkers) {
        //変位の幅を計測
        double xmaxd = (int)Math.ceil(walkers.get(0).getX());
        double xmind = xmaxd;
        for (WalkerExt w : walkers) {
            xmaxd = Math.max(xmaxd, w.getX());
            xmind = Math.min(xmind, w.getX());
        }
        int xmax = (int)Math.ceil(xmaxd);
        int xmin = (int)Math.floor(xmind);
        //ヒストグラムの生成
        int h[] = new int[xmax - xmin + 1];
        for (WalkerExt w : walkers) {
            int k = (int)Math.floor(w.getX() - xmin);
            h[k]++;
        }
        //ヒストグラムをリストに変換
        //ヒストグラムから相対頻度に変換
        List<Point2D.Double> list = Utils.createList();
        for (int i = 0; i < h.length; i ++) {
            double x = i + xmin+0.5;
//            //ヒストグラムの幅が2であることに注意
            double y = (double) h[i] / walkers.size();
            list.add(new Point2D.Double(x, y));
        }
        return list;
    }

}
