package com.texoit.interval;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IntervalDto {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
