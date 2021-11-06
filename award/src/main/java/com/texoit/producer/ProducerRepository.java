package com.texoit.producer;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProducerRepository extends JpaRepository<Producer, Integer> {
}
