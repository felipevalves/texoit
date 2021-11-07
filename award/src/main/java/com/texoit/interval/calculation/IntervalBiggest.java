package com.texoit.interval.calculation;

import com.texoit.award.entity.Award;
import com.texoit.interval.IntervalDto;
import com.texoit.producer.Producer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalBiggest implements IntervalCalculation {

    @Override
    public List<IntervalDto> calculate(Producer producer) {

        int intervalAux = Integer.MIN_VALUE;

        List<IntervalDto> listInterval = new ArrayList<>();
        List<Award> list = getAwardsSorted(producer);
        Award current;
        Award last = list.get(list.size() - 1);

        for (int i = list.size() -2 ; i >= 0 ; i--) {

            current = list.get(i);

            if (!last.isWinner() || !current.isWinner()) {
                last = current;
                continue;
            }

            if (intervalAux > last.getYear() - current.getYear()) {
                last = current;
                continue;
            }

            intervalAux = last.getYear() - current.getYear();

            IntervalDto interval = getIntervalDto(producer, intervalAux, current, last);

            listInterval.add(interval);

            last = current;
        }

        int maxInterval = listInterval.stream()
                .mapToInt(IntervalDto::getInterval)
                .max()
                .orElse(0);

        return listInterval.stream().filter(i -> i.getInterval() == maxInterval).toList();
    }

    private List<Award> getAwardsSorted(Producer producer) {
        return producer.getListAward().stream()
                .sorted(Comparator.comparingInt(Award::getYear)).toList();
    }

    private IntervalDto getIntervalDto(Producer producer, int intervalAux, Award current, Award last) {

        return IntervalDto.builder()
                .interval(intervalAux)
                .producer(producer.getName())
                .previousWin(current.getYear())
                .followingWin(last.getYear()).build();
    }
}
