package com.danceschool.danceschool.student;


import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MemoryBasedStudentRepositoryTests {

    @BeforeEach
    public void setUp() {
        System.out.println("preparation");
        PersonalData personalData = new PersonalData("name", "surname", "address");
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);
    }

    @AfterEach
    public void tearDown() {
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .getStudentList().clear();
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
    @DisplayName("should create second student when get correct data")
    public void shouldAddSecondStudentToStudentList() {
        //given
        PersonalData personalData = new PersonalData("name2", "surname2", "address2");
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);
        //when
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList().size();
        //then
        assertEquals(2, actualSize);
    }


    @Test
    @DisplayName("should show student interior - do method toString on Student")
    public void shouldShowStudentInterior() {
        //given
        String surname = "surname";
        //when
        List<Student> studentList = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().getStudentList();
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().readStudent(surname);
        boolean prawdaWyjdzieNaJaw = surname.equals(studentList.get(0).getSurname());
        //than
        assertTrue(prawdaWyjdzieNaJaw);
    }

    @Test
    @DisplayName("should change \"name2\" to \"newName\", same with surname2 and address2")
    public void shouldReplacePersonalData() {
        //given
        PersonalData personalData = new PersonalData("name2", "surname2", "address2");
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);

        PersonalData newPersonalData = new PersonalData("newName", "newSurname", "newAddress");
        String surnameToEdition = "surname2";

        //when
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .updateStudent(surnameToEdition, newPersonalData, Level.PROFESSIONAL);
        String actualName = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .getStudentList().get(1).getName();
        String expectedName = newPersonalData.getName();

        //than
        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("should delete student from the studentList and left empty studentList")
    public void shouldDeleteStudent() {
        //given
        String studentNameToDelete = "surname";

        //when
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .deleteStudent(studentNameToDelete);
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .size();
        //than
        assertEquals(0, actualSize);
    }
}