package com.texoit.interval.calculation;

import com.texoit.shared.util.Constant;

public class IntervalSimpleFactory {

    private IntervalSimpleFactory() {
    }

    public static IntervalCalculation create(Constant interval) {

        return switch (interval) {
            case INTERVAL_FASTER -> new IntervalFaster();
            default -> new IntervalBiggest();
        };
    }
}
