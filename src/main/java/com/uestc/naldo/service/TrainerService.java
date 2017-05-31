package com.uestc.naldo.service;

import com.uestc.naldo.domain.Trainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TrainerService {

    int add(Trainer trainer);

    int deleteById(Long id);

    int update(Trainer trainer);

    List<Trainer> queryTrainerListAll();

    Trainer queryTrainerById(Long id);

    List<Trainer> queryTrainerByName(String name);

    Trainer login(String username, String password);

}
