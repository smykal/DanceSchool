package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.PersonalData;
import com.danceschool.danceschool.student.Student;

import java.util.Map;

public interface SecretarySkills {
    void addTeacher(PersonalData personalData);
    void addStudent();
    void removeTeacher();
    void removeStudent();
    Map<Integer, Student> setUpGroup();
}
