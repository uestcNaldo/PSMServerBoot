package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.AttendanceMapper;
import com.uestc.naldo.domain.Attendance;
import com.uestc.naldo.domain.AttendanceItem;
import com.uestc.naldo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        return this.attendanceMapper.deleteById(id);
    }

    @Override
    public int update(Attendance attendance) {
        return this.attendanceMapper.update(attendance);
    }

    @Override
    public Attendance queryAttendanceById(Long id) {
        return this.attendanceMapper.queryAttendanceById(id);
    }

    @Override
    public List<Attendance> queryAttendanceListByTid(Long tid) {
        return this.attendanceMapper.queryAttendanceListByTid(tid);
    }

    @Override
    public List<Attendance> queryAttendanceListAll() {
        return this.attendanceMapper.queryAttendanceListAll();
    }

    @Override
    public List<AttendanceItem> queryAttendanceItemListAll() {
        return this.attendanceMapper.queryAttendanceItemListAll();
    }

    @Override
    public List<AttendanceItem> queryAttendanceItemListByNameAndDate(String name, Date date) {
        return this.attendanceMapper.queryAttendanceItemListByNameAndDate(name, date);
    }
}
