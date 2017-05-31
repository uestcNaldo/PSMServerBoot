package com.uestc.naldo.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;

public class Feedback {

    private Long id;

    private String title;

    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    private Long oid;

    public Feedback(){}

    public Feedback(Long id, String title, String content, Date date, Long oid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.oid = oid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }
}
