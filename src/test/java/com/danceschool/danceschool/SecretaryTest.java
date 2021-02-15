package com.danceschool.danceschool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SecretaryTest {


    @Test
    @DisplayName("should show content of group")
    void showGroupMembersTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"),Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR,teacherAdam,studentSetAdam);
        Level actualLevel = group01.level;

        //when
        madzia.addStudent(group01,new Student.Builder(new PersonalData("Janek", "Romanek"),Level.AMATEUR).build());
        Level expectedLevel = Level.AMATEUR;
        //then
        assertEquals(actualLevel, expectedLevel);
    }

    @Test
    @DisplayName("should replace group.teacher variable")
    void changeTeacherTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"),Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR,teacherAdam,studentSetAdam);
        Teacher actualTeacher = group01.teacher;

        //when
        madzia.changeTeacher(group01,new Teacher.Builder(new PersonalData("Nowy","Nauczyciel"),Level.PROFFESIONAL).build());
        Teacher expectedTeacher = group01.teacher;

        //then
        assertFalse(actualTeacher.equals(expectedTeacher), "Skoro to nie te same obiekty to jest ok");
    }

    @Test
    @DisplayName("should add Student to group.studentSet")
    void addStudentTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"),Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR,teacherAdam,studentSetAdam);
        madzia.addStudent(group01,new Student.Builder(new PersonalData("Ania","Adamczyk"),Level.AMATEUR).build());
        int actualSize = group01.studentSet.size();
        //when
        madzia.addStudent(group01,new Student.Builder(new PersonalData("Jola","Mickiewicz"),Level.AMATEUR).build());
        int expectedSize = group01.studentSet.size();

        //then
        assertTrue(expectedSize>actualSize);

    }

    @Test
    @DisplayName("should erase Student from group.studentSet")
    void removeStudentTest() {
        //given
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"),Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR,teacherAdam,studentSetAdam);
        madzia.addStudent(group01,new Student.Builder(new PersonalData("Ania","Adamczyk"),Level.AMATEUR).build());
        int actualSize = group01.studentSet.size();
        //when
        madzia.removeStudent(group01,new Student.Builder(new PersonalData("Ania","Adamczyk"),Level.AMATEUR).build());
        int expectedSize = group01.studentSet.size();

        //then
        assertTrue(expectedSize<actualSize);
    }
}