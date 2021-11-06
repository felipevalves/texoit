package com.texoit.award.repository;

import com.texoit.award.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Award, Integer> {
}
