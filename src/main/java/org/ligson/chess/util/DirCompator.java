package org.ligson.chess.util;

import org.ligson.chess.enums.Dir;
import org.ligson.chess.model.QiZi;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/1/18.
 */
public class DirCompator implements Comparator<QiZi> {
    private Dir from;
    private Dir to;

    public Dir getFrom() {
        return from;
    }

    public void setFrom(Dir from) {
        this.from = from;
    }

    public Dir getTo() {
        return to;
    }

    public void setTo(Dir to) {
        this.to = to;
    }

    public DirCompator(Dir from, Dir to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compare(QiZi o1, QiZi o2) {

        if ((from == Dir.LEFT) && (to == Dir.RIGHT)) {
            return o1.getColIdx() - o2.getIdx();
        } else if ((from == Dir.UP) && (to == Dir.DOWN)) {
            return o1.getRowIdx() - o2.getRowIdx();
        } else if ((from == Dir.LEFT_UP) && (to == Dir.RIGHT_DOWN)) {
            if ((o1.getRowIdx() - o2.getRowIdx() > 0) && (o1.getColIdx() - o2.getColIdx() > 0)) {
                return 1;
            } else {
                return -1;
            }
        } else if ((from == Dir.RIGHT_UP) && (to == Dir.LEFT_DOWN)) {
            if ((o1.getRowIdx() - o2.getRowIdx() > 0) && (o1.getColIdx() - o2.getColIdx() < 0)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }

    }
}
