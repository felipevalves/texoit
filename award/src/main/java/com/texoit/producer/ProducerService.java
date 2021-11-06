package com.texoit.producer;

import java.util.List;

public interface ProducerService {

    List<Producer> findAll();

    void saveAll(List<Producer> producers);
    List<Producer> findProducerWithMoreThanOneMovie();
}
