package com.texoit.interval.calculation;

import com.texoit.award.entity.Award;
import com.texoit.interval.IntervalDto;
import com.texoit.producer.Producer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalFaster implements IntervalCalculation {

    @Override
    public List<IntervalDto> calculate(Producer producer) {

        int intervalAux = Integer.MAX_VALUE;

        List<IntervalDto> listInterval = new ArrayList<>();
        List<Award> list = getAwardsSorted(producer);
        Award current;
        Award last = list.get(list.size() - 1);

        for (int i = list.size() -2 ; i >= 0 ; i--) {

            current = list.get(i);

            if (intervalAux < last.getYear() - current.getYear()) {
                last = list.get(i);
                continue;
            }

            intervalAux = last.getYear() - current.getYear();

            IntervalDto interval = getIntervalDto(producer, intervalAux, current, last);
            listInterval.add(interval);

            last = list.get(i);
        }

        return listInterval;
    }

    private IntervalDto getIntervalDto(Producer producer, int intervalAux, Award current, Award last) {

        return IntervalDto.builder()
                .interval(intervalAux)
                .producer(producer.getName())
                .previousWin(current.getYear())
                .followingWin(last.getYear()).build();
    }

    private List<Award> getAwardsSorted(Producer producer) {
        return producer.getListAward().stream()
                .filter(Award::isWinner)
                .sorted(Comparator.comparingInt(Award::getYear)).toList();
    }
}
