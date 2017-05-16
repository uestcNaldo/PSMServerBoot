package com.uestc.naldo.service;

import com.uestc.naldo.domain.School;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SchoolService {

    int add(School school);

    int deleteById(Long id);

    int update(School school);

    School querySchoolById(Long id);



}
