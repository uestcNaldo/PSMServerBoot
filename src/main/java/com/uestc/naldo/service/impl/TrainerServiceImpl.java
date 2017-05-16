package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.TrainerMapper;
import com.uestc.naldo.domain.Trainer;
import com.uestc.naldo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    @Autowired
    TrainerMapper trainerMapper;

    @Override
    public int add(Trainer trainer) {

        return this.trainerMapper.add(trainer);
    }

    @Override
    public int deleteById(Long id) {

        return this.trainerMapper.deleteById(id);
    }

    @Override
    public int update(Trainer trainer) {

        return this.trainerMapper.update(trainer);
    }

    @Override
    public List<Trainer> queryTrainerListAll() {

        return this.trainerMapper.queryTrainerListAll();
    }

    @Override
    public Trainer queryTrainerById(Long id) {

        return this.trainerMapper.queryTrainerById(id);
    }

    @Override
    public List<Trainer> queryTrainerByName(String name) {

        return this.trainerMapper.queryTrainerListByName(name);
    }
}
