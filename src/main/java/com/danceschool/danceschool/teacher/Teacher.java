package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.PersonalDataName;
import com.danceschool.danceschool.PersonalDataSurname;

public interface Teacher {
    void teach();

    PersonalDataName getPersonalName();
    PersonalDataSurname getPersonalSurname();

}
