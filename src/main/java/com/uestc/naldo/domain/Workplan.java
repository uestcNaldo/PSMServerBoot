package com.uestc.naldo.domain;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Naldo on 2017/5/15.
 */
public class Workplan {

    private Long id;

    private String title;

    private String content;

    private Date date;

    private Time time;

    private Long aid;

    public Workplan(){}

    public Workplan(Long id, String title, String content, Date date, Long aid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
