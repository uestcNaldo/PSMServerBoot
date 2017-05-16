package com.uestc.naldo.domain;

/**
 * Created by Naldo on 2017/5/15.
 */
public class School {

    private Long id;

    private String name;

    private String contact;

    private String intro;

    private String phone;

    private String address;

    public School(){}

    public School(Long id, String name, String contact, String intro, String phone, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.intro = intro;
        this.phone = phone;
        this.address = address;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
