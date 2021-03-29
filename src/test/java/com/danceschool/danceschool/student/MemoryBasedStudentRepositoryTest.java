package com.danceschool.danceschool.student;


import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class MemoryBasedStudentRepositoryTest {

    @BeforeEach
    public void setUp() throws IOException {
        System.out.println("preparation");
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("testName")
                .withSurname("testSurname")
                .withAddress("testAddress")
                .build();
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);
    }

    @AfterEach
    public void tearDown() {
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .deleteAllStudents();
        System.out.println("fin \n");
    }

    @Test
    @DisplayName("should create student")
    public void shouldCreateStudentAndPutIntoStudentList() {
        //given @BeforeEach is enough to prepare test

        //when
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList().size();
        //then
        assertEquals(1, actualSize);
    }

    @Test
    @DisplayName("should add second student to student list")
    public void shouldAddSecondStudentToStudentList() throws IOException {
        //given
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("studentTestName")
                .withSurname("studentTestSurname")
                .withAddress("studentTestAddress")
                .build();
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);
        Student expectedStudent = new Student.Builder()
                .personalData(personalData)
                .level(level)
                .build();
        //when
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList().size();

        Student actualStudent = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .get(1);

        //then
        assertEquals(2, actualSize);
        assertEquals(expectedStudent, actualStudent);
    }


    @Test
    @DisplayName("should show student interior - do method toString on Student")
    public void shouldShowStudentInterior() throws IOException {
        //given
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(new PersonalData.PersonalDataBuilder()
                                .withName("testName")
                                .withSurname("testSurname")
                                .withAddress("test Address")
                                .build()
                        , Level.PROFESSIONAL);

        UUID uuid = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList().get(0).getId();
        //when
        Student actualStudent = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .readStudent(uuid);
        //than
        assertNotNull(actualStudent);
    }

    @Test
    @DisplayName("should change \"name2\" to \"newName\", same with surname2 and address2")
    public void shouldReplacePersonalData() throws IOException {
        //given
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("name2")
                .withSurname("surname2")
                .withAddress("address2")
                .build();
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);

        UUID uuidToUpdate = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .get(0).getId();

        PersonalData newPersonalData = new PersonalData.PersonalDataBuilder()
                .withName("newName")
                .withSurname("newSurname")
                .withAddress("newAddress")
                .build();
        //when
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .updateStudent(uuidToUpdate, newPersonalData, Level.PROFESSIONAL);
        String actualName = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .getStudentList().get(0).getName();
        String expectedName = newPersonalData.getName();

        //than
        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("should delete student from the studentList and left empty studentList")
    public void shouldDeleteStudent() throws IOException {
        //given
        PersonalData personalData01 = new PersonalData.PersonalDataBuilder()
                .withName("testName01")
                .withSurname("testSurname01")
                .withAddress("testAddress01")
                .build();
        Level level01 = Level.AMATEUR;

        PersonalData personalData02 = new PersonalData.PersonalDataBuilder()
                .withName("testName02")
                .withSurname("testSurname02")
                .withAddress("testAddress02")
                .build();
        Level level02 = Level.PROFESSIONAL;

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData01,level01);
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData02,level02);
        UUID uuidToDelete = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .get(1)
                .getId();
        //when
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .deleteStudent(uuidToDelete);
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .size();
        //than
        assertEquals(2,actualSize);
    }
}