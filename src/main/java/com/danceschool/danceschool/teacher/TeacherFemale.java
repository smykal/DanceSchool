package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.PersonalInformation;

public class TeacherFemale implements Teacher {
    private PersonalInformation personalInformation;

    public TeacherFemale(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    @Override
    public void teach() {

    }

    @Override
    public String getPersonalName(String nameOrSurname) {
        if (nameOrSurname.equals("name")) {
            return this.getPersonalInformation().getName();
        } else {
            return this.getPersonalInformation().getSurname();
        }

    }
}
