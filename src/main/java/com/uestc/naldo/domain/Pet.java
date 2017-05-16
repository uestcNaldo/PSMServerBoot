package com.uestc.naldo.domain;

import java.sql.Date;

/**
 * Created by Naldo on 2017/5/15.
 */
public class Pet {

    private Long id;

    private String name;

    private String sex;

    private Integer age;

    private String species;

    private String hrStatus;

    private Date start;

    private Date end;

    private Long oid;

    private Long cid;

    public Pet(){}

    public Pet(Long id, String name, String sex, Integer age, String species, String hrStatus, Date start, Date end, Long oid, Long cid) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.species = species;
        this.hrStatus = hrStatus;
        this.start = start;
        this.end = end;
        this.oid = oid;
        this.cid = cid;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
}
