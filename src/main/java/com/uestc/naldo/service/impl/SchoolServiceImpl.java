package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.SchoolMapper;
import com.uestc.naldo.domain.School;
import com.uestc.naldo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int add(School school) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(School school) {

        return this.schoolMapper.update(school);
    }

    @Override
    public School querySchoolById(Long id) {

        return this.schoolMapper.querySchool();
    }

}
