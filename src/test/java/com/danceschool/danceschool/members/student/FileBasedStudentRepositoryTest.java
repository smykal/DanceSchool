package com.danceschool.danceschool.members.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileBasedStudentRepositoryTest {

    @BeforeEach
    void setUp() {
        getFileBasedStudentRepositoryInstance();
        getFileBasedStudentRepositoryInstance()
                .createNewStudentList();
        PersonalData testPersonalData01 = new PersonalData.PersonalDataBuilder()
                .withName("testName01")
                .withSurname("testSurname01")
                .withAddress("testAddress01")
                .build();

        PersonalData testPersonalData02 = new PersonalData.PersonalDataBuilder()
                .withName("testName02")
                .withSurname("testSurname02")
                .withAddress("testAddress02")
                .build();

        getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData01, Level.PROFESSIONAL);
        getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData02, Level.AMATEUR);
    }

    private FileBasedStudentRepository getFileBasedStudentRepositoryInstance() {
        return FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance();
    }

    @AfterEach
    void tearDown() {
        getFileBasedStudentRepositoryInstance()
                .deleteNewCsvStudentList();
    }

    @Test
    @DisplayName("should add student to csv file")
    void shouldCreateStudent() {
        //given
        PersonalData testPersonalData = new PersonalData
                .PersonalDataBuilder()
                .withName("testName")
                .withSurname("testSurname")
                .withAddress("testAddress")
                .build();

        //when
        UUID actualUUID = getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData,Level.AMATEUR);

        //then
        Student student = getFileBasedStudentRepositoryInstance().readStudent(actualUUID);
        UUID expectedUUID = student.getId();

        assertEquals(actualUUID, expectedUUID);

    }

    @Test
    @DisplayName ("test is comparing expected values of student with shown by readStudent() method")
    void readStudent() {
        //given
        PersonalData testPersonalData = new PersonalData
                .PersonalDataBuilder()
                .withName("testName")
                .withSurname("testSurname")
                .withAddress("testAddress")
                .build();
        UUID actualUUID = getFileBasedStudentRepositoryInstance().createStudent(testPersonalData, Level.AMATEUR);

        //when
        Student actualStudent = getFileBasedStudentRepositoryInstance().readStudent(actualUUID);

        //then
        String actualName = actualStudent.getName();
        String expectedName = testPersonalData.getName();

        String actualSurname = actualStudent.getSurname();
        String expectedSurname = testPersonalData.getSurname();

        String actualAddress = actualStudent.getCity();
        String expectedAddress = testPersonalData.getAddress();

        UUID expectedUUID = actualStudent.getId();

        assertEquals(actualName,expectedName);
        assertEquals(actualSurname,expectedSurname);
        assertEquals(actualAddress,expectedAddress);
        assertEquals(actualUUID,expectedUUID);
    }

    @Test
    @DisplayName("should check is new parameters of student are equals to expected")
    void updateStudent() {
        //given
        PersonalData testPersonalData = new PersonalData
                .PersonalDataBuilder()
                .withName("testName")
                .withSurname("testSurname")
                .withAddress("testAddress")
                .build();
        Level testLevel = Level.AMATEUR;
        UUID actualUUID = getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData, testLevel);
        PersonalData testNewPersonalData = new PersonalData
                .PersonalDataBuilder()
                .withName("newTestName")
                .withSurname("newTestSurname")
                .withAddress("newTestAddress")
                .build();
        Level newTestLevel = Level.PROFESSIONAL;

        //when
        getFileBasedStudentRepositoryInstance()
                .updateStudent(actualUUID, testNewPersonalData, Level.PROFESSIONAL);

        //than
        Student actualStudent = getFileBasedStudentRepositoryInstance()
                .readStudent(actualUUID);
        String actualName = actualStudent.getName();
        String expectedName = testNewPersonalData.getName();
        String actualSurname = actualStudent.getSurname();
        String expectedSurname = testNewPersonalData.getSurname();
        String actualAddress = actualStudent.getCity();
        String expectedAddress = testNewPersonalData.getAddress();
        UUID expectedUUID = actualStudent.getId();

        assertEquals(expectedName,actualName);
        assertEquals(expectedSurname,actualSurname);
        assertEquals(expectedAddress,actualAddress);
        assertEquals(expectedUUID,actualUUID);
    }

    @Test
    void deleteStudent() {
    }

    @Test
    @DisplayName("should create new csv file")
    void shouldCreateNewStudentCsvList() {
        //given
        final String PATH = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";
        getFileBasedStudentRepositoryInstance()
                .createNewStudentList();
        File f = new File(PATH);
        //when
        boolean actual = f.exists();

        //than
        assertTrue(actual);
    }

    @Test
    void readAllStudents() {
    }

    @Test
    void deleteNewCsvStudentList() {
    }
}