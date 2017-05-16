package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.AttendanceMapper;
import com.uestc.naldo.domain.Attendance;
import com.uestc.naldo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Service
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    AttendanceMapper attendanceMapper;


    @Override
    public int add(Attendance attendance) {
        return this.attendanceMapper.add(attendance);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Attendance attendance) {
        return this.attendanceMapper.update(attendance);
    }

    @Override
    public List<Attendance> queryAttendanceListByTid(Long tid) {
        return this.attendanceMapper.queryAttendanceListByTid(tid);
    }

    @Override
    public List<Attendance> queryAttendanceListAll() {
        return this.attendanceMapper.queryAttendanceListAll();
    }
}
