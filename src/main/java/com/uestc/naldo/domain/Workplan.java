package com.uestc.naldo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;


public class Workplan {

    private Long id;

    private String title;

    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private Date date;

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

}
