package com.texoit.interval;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class IntervalDto {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
