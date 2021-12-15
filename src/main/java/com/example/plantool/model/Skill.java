package com.example.plantool.model;

/**
 * Author: Jonas Munk
 *
 * Skill model class
 */

public class Skill {
    // Private fields
    private int skillId;
    private String skillName;

    // Constructor
    public Skill(int skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    // No-Args constructor
    public Skill() {
    }

    // Getters and Setters
    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    // Overridden toString method
    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
