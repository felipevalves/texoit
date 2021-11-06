package com.texoit.interval;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class IntervalContainer {
    private List<IntervalDto> min;
    private List<IntervalDto> max;
}
