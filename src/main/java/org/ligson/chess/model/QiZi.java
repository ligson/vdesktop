package org.ligson.chess.model;

import java.awt.*;

/**
 * Created by ligson on 2017/1/16.
 */
public class QiZi {
    private Point point;
    private boolean black = true;
    private int rowIdx;
    private int colIdx;
    private int idx;
    public static final int qiziR = 10;

    public QiZi(Point point, boolean black, int rowIdx, int colIdx, int idx) {
        this.point = point;
        this.black = black;
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.idx = idx;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public void setRowIdx(int rowIdx) {
        this.rowIdx = rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }

    public void setColIdx(int colIdx) {
        this.colIdx = colIdx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void paint(Graphics graphics) {
        if (black) {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(Color.WHITE);
        }
        graphics.fillOval(point.x - qiziR, point.y - qiziR, qiziR * 2, qiziR * 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QiZi qiZi = (QiZi) o;

        if (rowIdx != qiZi.rowIdx) return false;
        return colIdx == qiZi.colIdx;

    }

    @Override
    public int hashCode() {
        int result = rowIdx;
        result = 31 * result + colIdx;
        return result;
    }
}
