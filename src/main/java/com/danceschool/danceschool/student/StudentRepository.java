package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

public interface StudentRepository {


    void createStudent(PersonalData personalData, Level level);
    void readStudent(String surname);
    void updateStudent(String surname, PersonalData newPersonalData, Level newLevel);
    void deleteStudent(String surname);
}
