package com.example.plantool.model;

import java.util.ArrayList;

/**
 * Author: Kevin Funch
 *
 * Model Member Class
 */

public class Member {

    // Private fields
    private int userId;
    private String name;
    private String email;
    private String password;
    private ArrayList<String> skills;
    private int isLeader;

    // Initial Constructor
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Overloaded constructor
    public Member(String name, String email, String password, int isLeader) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLeader = isLeader;
    }

    // No-Args constructor
    public Member(){}

    // Getters and Setters
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

    // Overridden toString method
    @Override
    public String toString() {
        return "Member{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", skills=" + skills +
                ", isLeader=" + isLeader +
                '}';
    }
}