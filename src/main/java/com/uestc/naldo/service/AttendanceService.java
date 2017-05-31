package com.uestc.naldo.service;

import com.uestc.naldo.domain.Attendance;
import com.uestc.naldo.domain.AttendanceItem;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface AttendanceService {

    int add(Attendance attendance);

    int deleteById(Long id);

    int update(Attendance attendance);

    Attendance queryAttendanceById(Long id);

    List<Attendance> queryAttendanceListByTid(Long tid);

    List<Attendance> queryAttendanceListAll();

    List<AttendanceItem> queryAttendanceItemListAll();

    List<AttendanceItem> queryAttendanceItemListByNameAndDate(String name, Date date);

}
