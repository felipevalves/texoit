package com.texoit.interval.calculation;

import com.texoit.interval.IntervalDto;
import com.texoit.producer.Producer;

public interface IntervalCalculation {

    IntervalDto calculate(Producer producer);

}
