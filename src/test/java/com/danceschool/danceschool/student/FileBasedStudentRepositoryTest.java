package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Persistable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FileBasedStudentRepositoryTest {

    @BeforeEach
    void setUp() {
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance();
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createNewStudentList();
        PersonalData testPersonalData01 = new PersonalData.PersonalDataBuilder()
                .withName("testName01")
                .withSurname("testSurname01")
                .withAddress("testAddress01")
                .build();
        Level testLevel1 = Level.PROFESSIONAL;

        PersonalData testPersonalData02 = new PersonalData.PersonalDataBuilder()
                .withName("testName02")
                .withSurname("testSurname02")
                .withAddress("testAddress02")
                .build();
        Level testLevel2 = Level.PROFESSIONAL;

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData01, testLevel1);
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData02, testLevel2);
    }

    @AfterEach
    void tearDown() {
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
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
        Level testLevel = Level.AMATEUR;
        Student student = new Student.Builder()
                .personalData(testPersonalData)
                .level(testLevel)
                .build();
        //when
        UUID testUUID = FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData,testLevel);
        //then
        assertNotNull(testUUID, "if true - that means that createStudent gave back UUID as a result" );
    }

    @Test
    @DisplayName ("method should give back String with users details")
    void readStudent() {
        //given
        String testSurname = "testSurname02";
        //when
        Student actual = FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .readStudent(UUID.fromString(testSurname));
        //then
        assertTrue(actual!=null,"Object exist");
    }

    @Test
    void updateStudent() {

    }

    @Test
    void deleteStudent() {
    }

    @Test
    @DisplayName("should create new csv file")
    void shouldCreateNewStudentCsvList() {
        //given
        final String PATH = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
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