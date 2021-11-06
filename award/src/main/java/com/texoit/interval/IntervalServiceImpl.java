package com.texoit.interval;

import com.texoit.interval.calculation.IntervalSimpleFactory;
import com.texoit.producer.Producer;
import com.texoit.producer.ProducerService;
import com.texoit.shared.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IntervalServiceImpl implements IntervalService {

    @Autowired
    private ProducerService producerService;

    @Override
    public IntervalContainer getContainerInterval() {

        var biggest = findWinnerBiggestInterval();
        var faster = findWinnerFasterInterval();

        return new IntervalContainer(faster, biggest);
    }

    @Override
    public List<IntervalDto> findWinnerBiggestInterval() {

        var listProducer = producerService.findProducerWithMoreThanOneMovie();

        var interval = IntervalSimpleFactory.create(Constant.INTERVAL_BIGGEST);

        List<IntervalDto> listInterval = listProducer.stream()
                .filter(Producer::isMoreThenOneWin)
                .map(interval::calculate).toList();

        int maxInterval = listInterval.stream()
                .mapToInt(IntervalDto::getInterval)
                .max()
                .orElse(0);

        return listInterval.stream().filter(i -> i.getInterval() == maxInterval).toList();
    }

    @Override
    public List<IntervalDto> findWinnerFasterInterval() {

        var listProducer = producerService.findProducerWithMoreThanOneMovie();

        var interval = IntervalSimpleFactory.create(Constant.INTERVAL_FASTER);

        List<IntervalDto> listInterval = listProducer.stream()
                .filter(Producer::isMoreThenOneWin)
                .map(interval::calculate).toList();

        int minInterval = listInterval.stream()
                .mapToInt(IntervalDto::getInterval)
                .min()
                .orElse(0);

        return listInterval.stream().filter(i -> i.getInterval() == minInterval).toList();
    }
}
