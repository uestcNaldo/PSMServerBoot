package com.uestc.naldo.service;

import com.uestc.naldo.domain.Workplan;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public interface WorkplanService {

    int add(Workplan workplan);

    int deleteById(Long id);

    int update(Workplan workplan);

    Workplan queryWorkplanById(Long id);

    List<Workplan> queryWorkplanListAll();


    List<Workplan> queryWorkplanListByTitleAndDate(String title, Date date);

}
