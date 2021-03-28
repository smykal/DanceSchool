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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    @DisplayName("should create student when get correct data")
    public void shouldAddStudentToStudentList() {
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
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
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
        assertEquals(expectedStudent,actualStudent);
    }


    @Test
    @DisplayName("should show student interior - do method toString on Student")
    public void shouldShowStudentInterior() {
        //given
        String surname = "testSurname";
        //when
        List<Student> studentList = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().getStudentList();
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().readStudent(UUID.fromString(surname));
        boolean prawdaWyjdzieNaJaw = surname.equals(studentList.get(0).getSurname());
        System.out.println(prawdaWyjdzieNaJaw);
        //than
        assertTrue(prawdaWyjdzieNaJaw);
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

        PersonalData newPersonalData = new PersonalData.PersonalDataBuilder()
                .withName("newName")
                .withSurname("newSurname")
                .withAddress("newAddress")
                .build();
        String surnameToEdition = "surname2";

        //when
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .updateStudent(UUID.fromString(surnameToEdition), newPersonalData, Level.PROFESSIONAL);
        String actualName = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .getStudentList().get(1).getName();
        String expectedName = newPersonalData.getName();

        //than
        assertEquals(expectedName, actualName);
    }

//    @Test
//    @DisplayName("should delete student from the studentList and left empty studentList")
//    public void shouldDeleteStudent() {
//        //given
//        String testStringUUID = "1bf58258-8e20-476c-984d-508e2e0083f3";
//        UUID testUUID = new UUID.fromString(testStringUUID);
//
//        //when
//        MemoryBasedStudentRepository
//                .getMemoryBasedStudentRepositoryInstance()
//                .deleteStudent(studentIdToDelete);
//        int actualSize = MemoryBasedStudentRepository
//                .getMemoryBasedStudentRepositoryInstance()
//                .getStudentList()
//                .size();
//        System.out.println(actualSize);
//        //than
//        assertEquals(0, actualSize);
//    }
}