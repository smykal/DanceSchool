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
    private MemoryBasedStudentRepository memoryBasedStudentRepositoryInstance = MemoryBasedStudentRepository
            .getMemoryBasedStudentRepositoryInstance();

    @BeforeEach
    public void setUp() throws IOException {
        System.out.println("preparation");
        PersonalData personalData = createPersonalData("testName", "testSurname", "testAddress");
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
        //given
        PersonalData personalData = createPersonalData("testName", "testSurname", "testAddress");
        Level level = Level.AMATEUR;
        Student expectedStudent = createExpectedStudent(personalData, level);

        //when
        List<Student> students = memoryBasedStudentRepositoryInstance.getStudentList();

        //then
        int actualSize = students.size();
        Student actualStudent = students.get(0);

        assertEquals(1, actualSize);
        assertEquals(actualStudent, expectedStudent);
    }

    private Student createExpectedStudent(PersonalData personalData, Level level) {
        return new Student.Builder()
                .personalData(personalData)
                .level(level)
                .build();
    }

    @Test
    @DisplayName("should add second student to student list")
    public void shouldAddSecondStudentToStudentList() throws IOException {
        //given
        PersonalData personalData = createPersonalData("studentTestName", "studentTestSurname", "studentTestAddress");
        Level level = Level.AMATEUR;
        Student expectedStudent = createExpectedStudent(personalData, level);

        //when
        UUID studentId = memoryBasedStudentRepositoryInstance
                .createStudent(personalData, level);

        //then
        Student anotherExpectedStudent = memoryBasedStudentRepositoryInstance.readStudent(studentId);
        List<Student> studentsList = memoryBasedStudentRepositoryInstance
                .getStudentList();
        int actualSize = studentsList.size();

        assertEquals(2, actualSize);
        assertEquals(expectedStudent,anotherExpectedStudent);
    }

    private PersonalData createPersonalData(String studentTestName, String studentTestSurname, String studentTestAddress) {
        return new PersonalData.PersonalDataBuilder()
                .withName(studentTestName)
                .withSurname(studentTestSurname)
                .withAddress(studentTestAddress)
                .build();
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
        PersonalData personalData = createPersonalData("name2", "surname2", "address2");
        Level level = Level.AMATEUR;
        MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, level);

        UUID uuidToUpdate = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList()
                .get(0).getId();

        PersonalData newPersonalData = createPersonalData("newName", "newSurname", "newAddress");
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
        PersonalData personalData01 = createPersonalData("testName01", "testSurname01", "testAddress01");
        Level level01 = Level.AMATEUR;

        PersonalData personalData02 = createPersonalData("testName02", "testSurname02", "testAddress02");
        Level level02 = Level.PROFESSIONAL;

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData01, level01);
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData02, level02);
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
        assertEquals(2, actualSize);
    }
}