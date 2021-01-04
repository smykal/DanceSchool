package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.PersonalData;
import com.danceschool.danceschool.PersonalDataName;
import com.danceschool.danceschool.PersonalDataSurname;

public class TeacherFemale implements Teacher {
 private PersonalData personalData;

    public TeacherFemale(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
    public PersonalDataName getPersonalName() {
        return null;
    }

    @Override
    public PersonalDataSurname getPersonalSurname() {
        return null;
    }

    @Override
    public void teach() {
    }
}
