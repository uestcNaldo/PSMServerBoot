package com.uestc.naldo.domain;

/**
 * Created by Naldo on 2017/5/15.
 */
public class Course {
    private Long id;

    private String name;

    private String inst;

    private String condition;

    private String duration;

    private String content;

    private String prise;

    private String note;

    public Course(){}

    public Course(Long id, String name, String inst, String condition, String duration, String content, String prise, String note) {
        this.id = id;
        this.name = name;
        this.inst = inst;
        this.condition = condition;
        this.duration = duration;
        this.content = content;
        this.prise = prise;
        this.note = note;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrise() {
        return prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
