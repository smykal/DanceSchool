package com.danceschool.danceschool;

public class PersonalData {
    private String name;
    private String surname;
    private Sex sex;
    private AdvanceLevel advanceLevel;

    public PersonalData(String name, String surname, Sex sex, AdvanceLevel advanceLevel) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.advanceLevel = advanceLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public AdvanceLevel getAdvanceLevel() {
        return advanceLevel;
    }

    public void setAdvanceLevel(AdvanceLevel advanceLevel) {
        this.advanceLevel = advanceLevel;
    }
}

