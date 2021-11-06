package com.texoit.interval;

import java.util.List;

public interface IntervalService {

    List<IntervalDto> findWinnerFasterInterval();
    List<IntervalDto> findWinnerBiggestInterval();
    IntervalContainer getContainerInterval();

}
