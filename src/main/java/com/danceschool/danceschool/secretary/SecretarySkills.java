package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.Address;
import com.danceschool.danceschool.Level;
import com.danceschool.danceschool.PersonalData;
import com.danceschool.danceschool.student.Trainee;
import com.danceschool.danceschool.teacher.Teacher;

import java.util.Map;

public interface SecretarySkills {
    Teacher createTeacher(PersonalData personalData, Address address);
    void addTeacher();

    Trainee createStudent(PersonalData personalData, Address address, Level level);
    void addStudent();


}
