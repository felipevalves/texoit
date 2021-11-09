package com.texoit.interval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IntervalTest {

    @Autowired
    private IntervalService intervalService;

    @Test
    void test_find_faster_interval_winner() {

        List<IntervalDto> listInterval = intervalService.findWinnerFasterInterval();
        Assertions.assertNotNull(listInterval);
        System.out.println(listInterval);
    }

    @Test
    void test_find_biggest_interval_winner() {

        List<IntervalDto> listInterval = intervalService.findWinnerBiggestInterval();
        Assertions.assertNotNull(listInterval);
        System.out.println(listInterval);
    }

    @Test
    void test_find_interval() {

        var container = intervalService.getContainerInterval();
        Assertions.assertNotNull(intervalService);
        System.out.println(container);
    }

}
