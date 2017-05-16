package com.uestc.naldo.domain;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Naldo on 2017/5/15.
 */
public class Attendance {

    private Long id;

    private Date date;

    private Time time;

    private String status;

    private Long tid;

    public Attendance(){}

    public Attendance(Long id, Date date, Time time, String status, Long tid) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tid = tid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
