package com.infernolounge.model;

import java.util.Date;

public class Client {
    private int id;
    private String name;
    private String phone;
    //email validation
    private String email;
    private String comment;
    //int validation
    private int card;
    //date validation
    private Date birthday;
    private int[] groupIds;

    public Client(int id, String name, String phone, String email, String comment, int card, Date birthday, int[] groupIds) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
        this.card = card;
        this.birthday = birthday;
        this.groupIds = groupIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(int[] groupIds) {
        this.groupIds = groupIds;
    }
}
