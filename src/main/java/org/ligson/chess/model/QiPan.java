package org.ligson.chess.model;

import org.ligson.chess.main.Main;
import org.ligson.chess.service.ChessService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;

/**
 * Created by ligson on 2017/1/16.
 */
public class QiPan {
    private int rowNum = 19;
    private int rowHeight = 32;
    private int width = rowNum * rowHeight;
    private Point startPos;
    private static boolean blackRole = true;
    private Point[][] allPoints = new Point[rowNum][rowNum];
    private Graphics graphics;
    private Main main;
    private ChessService chessService = new ChessService();
    private java.util.List<QiZi> qiZiList = new ArrayList<>();
    private boolean win = false;
    private List<QiZi> winQiZiList = new ArrayList<>();

    public void init() {
        win = false;
        qiZiList.clear();
        winQiZiList.clear();
    }

    public QiPan(Point startPos, Main main) {
        this.startPos = startPos;
        for (int i = 0; i < rowNum; i++) {
            int y = startPos.y + i * rowHeight;
            System.out.print("line:" + i);
            for (int j = 0; j < rowNum; j++) {
                int x = startPos.x + j * rowHeight;
                allPoints[i][j] = new Point(x, y);
                System.out.print("\t(" + x + "," + y + ")");
            }
            System.out.println();
        }
        this.main = main;
        init();
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
        //绘制线
        this.graphics = graphics;
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < rowNum; i++) {
            graphics.fillRect(startPos.x, startPos.y + i * rowHeight, width - rowHeight, 3);
            graphics.fillRect(startPos.x + i * rowHeight, startPos.y, 3, width - rowHeight);
        }

        //绘制五点
        int cx = (int) Math.floor(rowNum / 2.0);
        Point cPoint = allPoints[cx][cx];
        graphics.fillOval(cPoint.x - 6, cPoint.y - 6, 14, 14);
        Point luPoint = allPoints[3][3];
        graphics.fillOval(luPoint.x - 6, luPoint.y - 6, 14, 14);
        Point ldPoint = allPoints[rowNum - 3 - 1][3];
        graphics.fillOval(ldPoint.x - 6, ldPoint.y - 6, 14, 14);
        Point ruPoint = allPoints[3][rowNum - 3 - 1];
        graphics.fillOval(ruPoint.x - 6, ruPoint.y - 6, 14, 14);
        Point rdPoint = allPoints[rowNum - 3 - 1][rowNum - 3 - 1];
        graphics.fillOval(rdPoint.x - 6, rdPoint.y - 6, 14, 14);

        //绘制文字
        graphics.setFont(new Font("Tahoma", Font.BOLD, 12));
        for (int i = 0; i < rowNum; i++) {
            Point rowPoint = allPoints[i][0];
            Point colPoint = allPoints[0][i];


            graphics.drawString(((char) ('A' + i)) + "", rowPoint.x - 20, rowPoint.y + 5);
            graphics.drawString((i + 1) + "", colPoint.x - 5, colPoint.y - 10);
        }
        drawQizi();
    }

    public void drawWinMsg(Graphics graphics) {
        //graphics.setColor();
    }

    public void drawQizi() {
        for (QiZi qiZi : qiZiList) {
            qiZi.paint(this.graphics);
        }
        if (win) {
            for (QiZi qiZi : winQiZiList) {
                qiZi.paintWin(this.graphics);
            }
        }

    }

    public void processClick(MouseEvent e) {
        if (win) {
            return;
        }
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
                        boolean contains = false;
                        for (QiZi qiZi1 : qiZiList) {
                            if ((qiZi1.getColIdx() == qiZi.getColIdx()) && (qiZi.getRowIdx() == qiZi1.getRowIdx())) {
                                contains = true;
                                break;
                            }
                        }
                        if (!contains) {
                            qiZiList.add(qiZi);
                            blackRole = !blackRole;
                            main.repaint();
                            System.out.println("第" + (idx + 1) + "步," + (qiZi.isBlack() ? "黑" : "白") + "棋落子在第" + (i + 1) + "行,第" + (j + 1) + "列,棋子总数:" + qiZiList.size() + ",坐标:(" + qiZi.getPoint().x + "," + qiZi.getPoint().y + ")");
                            winQiZiList = chessService.check(qiZi, qiZiList);
                            if (winQiZiList.size() == 5) {
                                System.out.println((qiZi.isBlack() ? "黑" : "白") + "棋胜");
                                win = true;
                            }
                        }
                        break;
                    }
                }
            }
        } else {
            System.out.println("not process");
        }
    }

    public void huiqi() {
        if (win) {
            win = false;
        }
        if (qiZiList.size() > 0) {
            qiZiList.remove(qiZiList.size() - 1);
            blackRole = !blackRole;
        }
    }
}
