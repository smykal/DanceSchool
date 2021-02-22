package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Group;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class SecretaryTest {


    @Test
    @DisplayName("should show content of group")
    void showGroupMembersTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"), Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR, teacherAdam, studentSetAdam);


        //when


        //then

    }

    @Test
    @DisplayName("should replace group.teacher variable")
    void changeTeacherTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"), Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR, teacherAdam, studentSetAdam);


        //when


        //then
    }

    @Test
    @DisplayName("should add Student to group.studentSet")
    void addStudentTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"), Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR, teacherAdam, studentSetAdam);
        //when

        //then

    }

    @Test
    @DisplayName("should erase Student from group.studentSet")
    void removeStudentTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"), Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR, teacherAdam, studentSetAdam);
        //when

        //then
    }
}