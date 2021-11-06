package com.texoit.producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface ProducerRepository extends JpaRepository<Producer, Integer> {

    @Query(value = "SELECT * FROM PRODUCER " +
            "   WHERE ID_PRODUCER IN ( " +
            "   SELECT ID_PRODUCER FROM AWARD GROUP BY ID_PRODUCER HAVING COUNT(*) > 1 " +
            ")  ORDER BY ID_PRODUCER", nativeQuery = true)
    List<Producer> findProducerWithMoreThanOneMovie();

}
