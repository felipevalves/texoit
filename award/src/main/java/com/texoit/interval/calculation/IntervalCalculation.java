package com.texoit.interval.calculation;

import com.texoit.interval.IntervalDto;
import com.texoit.producer.Producer;

import java.util.List;

public interface IntervalCalculation {

    List<IntervalDto> calculate(Producer producer);
}
