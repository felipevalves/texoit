package com.texoit.interval.calculation;

import com.texoit.award.entity.Award;
import com.texoit.interval.IntervalDto;
import com.texoit.producer.Producer;

import java.util.Comparator;

public class IntervalBiggest implements IntervalCalculation {
    @Override
    public IntervalDto calculate(Producer producer) {
        IntervalDto interval = new IntervalDto();

        int intervalAux = Integer.MIN_VALUE;

        var list = producer.getListAward().stream()
                .filter(Award::isWinner)
                .sorted(Comparator.comparingInt(Award::getYear)).toList();

        Award last = list.get(list.size() - 1);
        for (int i = list.size() -2 ; i >= 0 ; i--) {

            if (intervalAux < last.getYear() - list.get(i).getYear()) {
                intervalAux = last.getYear() - list.get(i).getYear();

                interval.setInterval(intervalAux);
                interval.setProducer(producer.getName());
                interval.setPreviousWin(list.get(i).getYear());
                interval.setFollowingWin(last.getYear());
            }

            last = list.get(i);
        }

        return interval;
    }
}
