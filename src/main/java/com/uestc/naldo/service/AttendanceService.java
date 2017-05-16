package com.uestc.naldo.service;

import com.uestc.naldo.domain.Attendance;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface AttendanceService {

    int add(Attendance attendance);

    int deleteById(Long id);

    int update(Attendance attendance);

    List<Attendance> queryAttendanceListByTid(Long tid);

    List<Attendance> queryAttendanceListAll();

}
