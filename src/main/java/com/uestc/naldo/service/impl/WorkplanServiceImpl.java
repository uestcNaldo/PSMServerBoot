package com.uestc.naldo.service.impl;

import com.github.pagehelper.PageHelper;
import com.uestc.naldo.dao.WorkplanMapper;
import com.uestc.naldo.domain.Workplan;
import com.uestc.naldo.service.WorkplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
    }

    @Override
    public List<Workplan> queryWorkplanListAll() {

        return this.workplanMapper.queryWorkplanListAll();

    }
}
