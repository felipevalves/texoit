package com.texoit.interval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class IntervalContainer {
    private List<IntervalDto> min;
    private List<IntervalDto> max;
}
