package com.texoit.shared.util;

public enum Constant {
    POS_YEAR(0),
    POS_MOVIE(1),
    POS_PRODUCER(3),
    POS_WINNER(4),

    INTERVAL_FASTER,
    INTERVAL_BIGGEST;

    private int key;

    Constant() {
    }

    Constant(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
