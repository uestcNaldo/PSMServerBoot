package com.uestc.naldo.domain;

/**
 * Created by Naldo on 2017/5/15.
 */
public class Trainer {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String sex;

    private Integer age;

    private String email;

    private String position;

    private String maxim;

    private String intro;

    public Trainer(){}

    public Trainer(Long id, String username, String password, String name, String sex, Integer age, String email, String position, String maxim, String intro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.position = position;
        this.maxim = maxim;
        this.intro = intro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaxim() {
        return maxim;
    }

    public void setMaxim(String maxim) {
        this.maxim = maxim;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
