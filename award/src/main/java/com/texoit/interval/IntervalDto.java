package com.texoit.interval;

import lombok.Data;

@Data
public class IntervalDto {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
