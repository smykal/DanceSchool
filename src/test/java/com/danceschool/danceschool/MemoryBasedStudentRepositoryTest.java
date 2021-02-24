package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryBasedStudentRepositoryTest {
    @Test
    @DisplayName("should create student when get correct data")
    public void createStudentWithCorrectDataTest() {
        //given empty studentList and correct PersonalData object
        List<Student> studentList = new ArrayList<>();
        PersonalData personalData = new PersonalData("Name","Surname", "Address");
        Level level = Level.valueOf("AMATEUR");

        //when add a new student with correct fields
        studentList.add(new Student.Builder()
                .personalData(personalData)
                .level(level)
                .build());
        int expectedSize = studentList.size();
        int actualSize = 1;

        //then studentList should be one size bigger than before
        assertEquals(expectedSize,actualSize);
    }
    @Test
    @DisplayName("should print message when get wrong PersonalData ")
    public void createStudentWithoutCorrectDataTest() {
        //given empty studentList and wrong PersonalData object
        List<Student> studentList = new ArrayList<>();
        PersonalData personalData = new PersonalData(null,"Surname", "Address");
        Level level = Level.valueOf("AMATEUR");

        //when add a new student with wrong PersonalData fields
        studentList.add(new Student.Builder()
                .personalData(personalData)
                .level(level)
                .build());

        //then should give back error with message
        assertEquals();
    }
}

