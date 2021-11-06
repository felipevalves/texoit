package com.texoit.award;

import com.texoit.award.entity.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardRepository repository;

    @Override
    public void saveAll(List<Award> listAward) {

        if (listAward == null)
            throw new IllegalArgumentException("Award list must not be null");

        repository.saveAll(listAward);
    }

    @Override
    public List<Award> findAll() {
        return repository.findAll();
    }
}
