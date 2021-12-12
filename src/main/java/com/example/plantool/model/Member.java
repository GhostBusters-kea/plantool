package com.example.plantool.model;

import java.util.ArrayList;

public class Member {
    private int userId;
    private String name;
    private String email;
    private String password;
    private ArrayList<String> skills;
    private int isLeader;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member(String name, String email, String password, int isLeader) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLeader = isLeader;
    }

    public Member(){}

    public int getMemberId() {
        return userId;
    }

    public void setMemberId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public int getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(int isLeader) {
        this.isLeader = isLeader;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}