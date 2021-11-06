package com.texoit.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository repository;

    @Override
    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAll(List<Producer> producers) {

        if (producers == null)
            throw new IllegalArgumentException("Producer list must not be null");

        repository.saveAll(producers);
    }

    @Override
    public List<Producer> findProducerWithMoreThanOneMovie() {
        return repository.findProducerWithMoreThanOneMovie();
    }
}
