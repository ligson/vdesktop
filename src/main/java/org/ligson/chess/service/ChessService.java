package org.ligson.chess.service;

import org.ligson.chess.enums.Dir;
import org.ligson.chess.model.QiZi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ligson on 2017/1/16.
 */
public class ChessService {

    public List<QiZi> checkAll(QiZi current, List<QiZi> qiZiList, Dir dir) {
        List<QiZi> list = new ArrayList<>();
        int rowIdx, colIdx;
        if (dir == Dir.LEFT) {
            rowIdx = current.getRowIdx();
            colIdx = current.getColIdx() - 1;
        } else if (dir == Dir.RIGHT) {
            rowIdx = current.getRowIdx();
            colIdx = current.getColIdx() + 1;
        } else if (dir == Dir.UP) {
            rowIdx = current.getRowIdx() - 1;
            colIdx = current.getColIdx();
        } else if (dir == Dir.DOWN) {
            rowIdx = current.getRowIdx() + 1;
            colIdx = current.getColIdx();
        } else if (dir == Dir.LEFT_UP) {
            rowIdx = current.getRowIdx() - 1;
            colIdx = current.getColIdx() - 1;
        } else if (dir == Dir.LEFT_DOWN) {
            rowIdx = current.getRowIdx() + 1;
            colIdx = current.getColIdx() - 1;
        } else if (dir == Dir.RIGHT_UP) {
            rowIdx = current.getRowIdx() - 1;
            colIdx = current.getColIdx() + 1;
        } else {
            //else if (dir == Dir.RIGHT_DOWN)
            rowIdx = current.getRowIdx() + 1;
            colIdx = current.getColIdx() + 1;
        }
        QiZi qiZi = new QiZi(current.isBlack(), rowIdx, colIdx);
        int idx = qiZiList.indexOf(qiZi);
        if (idx >= 0) {
            list.add(qiZiList.get(idx));
            list.addAll(checkAll(qiZi, qiZiList, dir));
        }
        return list;
    }


    public List<QiZi> check(QiZi current, List<QiZi> qiZiList) {
        if (qiZiList.size() >= 9) {
            List<QiZi> list = new ArrayList<>();
            //检查同行
            list.add(current);
            list.addAll(checkAll(current, qiZiList, Dir.LEFT));
            list.addAll(checkAll(current, qiZiList, Dir.RIGHT));
            if (list.size() == 5) {
                return list;
            }
            //检查同列
            list.clear();
            list.add(current);
            list.addAll(checkAll(current, qiZiList, Dir.UP));
            list.addAll(checkAll(current, qiZiList, Dir.DOWN));
            if (list.size() == 5) {
                return list;
            }
            //检查从左上角到右下角
            list.clear();
            list.add(current);
            list.addAll(checkAll(current, qiZiList, Dir.LEFT_UP));
            list.addAll(checkAll(current, qiZiList, Dir.RIGHT_DOWN));
            if (list.size() == 5) {
                return list;
            }
            //检查从右上角到左下角
            list.clear();
            list.add(current);
            list.addAll(checkAll(current, qiZiList, Dir.RIGHT_UP));
            list.addAll(checkAll(current, qiZiList, Dir.LEFT_DOWN));
            if (list.size() == 5) {
                return list;
            }
        }
        return Collections.emptyList();
    }
}
