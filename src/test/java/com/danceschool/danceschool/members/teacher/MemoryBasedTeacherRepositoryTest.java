package com.danceschool.danceschool.members.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedTeacherRepositoryTest {

    @BeforeEach
    public void setUp() {
        System.out.println("Start Test");
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("teacherName")
                .withSurname("teacherSurname")
                .withAddress("teacherAddress")
                .build();
                Level teacherLevel = Level.AMATEUR;
        MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .createTeacher(personalData,teacherLevel);
    }
    @AfterEach
    public void tearDown() {
        MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .getTeacherList()
                .clear();
        System.out.println("Finish test \n");
    }

    @Test
    @DisplayName("should add new teacher to teacherList and increase teacherList to 1")
    public void shouldCreateTeacher() {
        //given
            //section @BeforeEach provide this part

        //when
        int actualSize = MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .getTeacherList()
                .size();

        //than
        assertEquals(1,actualSize);
    }

    @Test
    @DisplayName("should show content of teacher")
    public void shouldReadTeacher() {
        //given
        String surnameOfTeacherToRead = "teacherSurname";
        MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .readTeacher(surnameOfTeacherToRead);
        //when
        String actualSurname = MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .getTeacherList()
                .get(0)
                .getSurname();

        //than
        assertEquals("teacherSurname", actualSurname);
    }

    @Test
    @DisplayName("should change surname of teacher to \"private Kowalski\"")
    public void shouldUpdateTeacher() {
        //given
        String teacherToChange = "teacherSurname";
        PersonalData newPersonalData = new PersonalData.PersonalDataBuilder()
                .withName("szeregowy Kowalski")
                .withSurname("private Kowalski")
                .withAddress("Nowy address")
                .build();
        //when
        MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .updateTeacher(teacherToChange, newPersonalData,Level.PROFESSIONAL);
        String actualSurname = MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .getTeacherList()
                .get(0)
                .getSurname();
        //than
        assertEquals("private Kowalski", actualSurname);
    }

    @Test
    @DisplayName("should delete teacher and left empty teacherList")
    public void shouldDeleteTeacher() {
        //given
        String surnameToDelete = "teacherSurname";
        //when
        MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .deleteTeacher(surnameToDelete);
        int actualSize = MemoryBasedTeacherRepository
                .getMemoryBasedTeacherRepositoryInstance()
                .getTeacherList()
                .size();
        //than
        assertEquals(0,actualSize);
    }


}