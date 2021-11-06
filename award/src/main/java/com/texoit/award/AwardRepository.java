package com.texoit.award;

import com.texoit.award.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, Integer> {
}
