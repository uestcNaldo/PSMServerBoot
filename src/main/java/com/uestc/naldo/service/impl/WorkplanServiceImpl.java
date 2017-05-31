package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.WorkplanMapper;
import com.uestc.naldo.domain.Workplan;
import com.uestc.naldo.service.WorkplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class WorkplanServiceImpl implements WorkplanService{

    @Autowired
    WorkplanMapper workplanMapper;

    @Override
    public int add(Workplan workplan) {

        return this.workplanMapper.add(workplan);
    }

    @Override
    public int deleteById(Long id) {

        return this.workplanMapper.deleteById(id);
    }

    @Override
    public int update(Workplan workplan) {

        return this.workplanMapper.update(workplan);

    }

    @Override
    public Workplan queryWorkplanById(Long id) {

        return this.workplanMapper.queryWorkplanById(id);
    }

    @Override
    public List<Workplan> queryWorkplanListAll() {

        return this.workplanMapper.queryWorkplanListAll();

    }

    @Override
    public List<Workplan> queryWorkplanListByTitleAndDate(String title, Date date) {
        return this.workplanMapper.queryWorkplanListByTitleAndDate(title, date);
    }
}
