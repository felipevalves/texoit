package com.texoit.util;

public enum Constant {
    POS_YEAR(0),
    POS_MOVIE(1),
    POS_PRODUCER(3),
    POS_WINNER(4);

    private int key;
    Constant(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
