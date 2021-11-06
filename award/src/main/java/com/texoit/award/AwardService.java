package com.texoit.award;

import com.texoit.award.entity.Award;

import java.util.List;

public interface AwardService {

    void saveAll(List<Award> listAward);
    List<Award> findAll();

}
