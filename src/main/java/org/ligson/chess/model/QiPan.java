package org.ligson.chess.model;

import org.ligson.chess.main.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by ligson on 2017/1/16.
 */
public class QiPan {
    private int rowNum = 19;
    private int rowHeight = 32;
    private int width = rowNum * rowHeight;
    private Point startPos;
    private java.util.List<QiZi> qiZiList = new ArrayList<>();
    private static boolean blackRole = true;
    private Point[][] allPoints = new Point[rowNum][rowNum];
    private Graphics graphics;
    private Main main;

    public QiPan(Point startPos, Main main) {
        this.startPos = startPos;
        for (int i = 0; i < rowNum; i++) {
            int x = startPos.x + i * rowHeight;
            for (int j = 0; j < rowNum; j++) {
                int y = startPos.y + j * rowHeight;
                allPoints[i][j] = new Point(x, y);
            }
        }
        this.main = main;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Point getStartPos() {
        return startPos;
    }

    public void setStartPos(Point startPos) {
        this.startPos = startPos;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < rowNum; i++) {
            graphics.fillRect(startPos.x, startPos.y + i * rowHeight, width - rowHeight, 3);
            graphics.fillRect(startPos.x + i * rowHeight, startPos.y, 3, width - rowHeight);
        }
        this.graphics = graphics;
        drawQizi();
    }

    public void drawQizi() {
        for (QiZi qiZi : qiZiList) {
            qiZi.paint(this.graphics);
        }
    }

    public void processClick(MouseEvent e) {
        Point first = allPoints[0][0];
        Point last = allPoints[allPoints.length - 1][allPoints.length - 1];
        int clickPos = rowHeight / 2;
        if ((e.getX() >= (first.getX() - clickPos)) && (e.getY() >= (first.getY() - clickPos)) && (e.getX() <= last.getX() + clickPos) && (e.getY() <= last.getY() + clickPos)) {
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < rowNum; j++) {
                    Point point = allPoints[i][j];
                    double len = Math.sqrt(Math.pow(e.getX() - point.getX(), 2.0) + Math.pow(e.getY() - point.getY(), 2.0));
                    if (len <= clickPos) {
                        int idx = qiZiList.size();
                        QiZi qiZi = new QiZi(point, blackRole, i, j, idx);
                        if (!qiZiList.contains(qiZi)) {
                            qiZiList.add(qiZi);
                            blackRole = !blackRole;
                            main.repaint();
                            System.out.println("第" + (idx + 1) + "步," + (qiZi.isBlack() ? "黑" : "白") + "棋落子在第" + (j + 1) + "行,第" + (i + 1) + "列,棋子总数:" + qiZiList.size());
                        }
                        break;
                    }
                }
            }
        } else {
            System.out.println("not process");
        }
    }
}
