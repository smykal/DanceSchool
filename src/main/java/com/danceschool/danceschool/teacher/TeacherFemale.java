package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.PersonalData;

public class TeacherFemale implements Teacher {
    private PersonalData personalData;

    public TeacherFemale(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
    public void teach() {

    }

    @Override
    public String getPersonalInformation(String nameOrSurname) {
        if (nameOrSurname.equals("name")) {
            return this.getPersonalData().getName();
        } else {
            return this.getPersonalData().getSurname();
        }

    }
}
