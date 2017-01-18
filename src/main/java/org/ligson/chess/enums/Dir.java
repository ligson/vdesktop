package org.ligson.chess.enums;

/**
 * Created by ligson on 2017/1/18.
 */
public enum Dir {
    LEFT(1, "左"), RIGHT(2, "右"), UP(3, "上"), DOWN(4, "下"), LEFT_UP(5, "左上"), LEFT_DOWN(6, "左下"), RIGHT_UP(7, "右上"), RIGHT_DOWN(7, "右下");

    private int code;
    private String msg;

    Dir(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
