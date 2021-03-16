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

import static org.junit.jupiter.api.Assertions.*;

class FileBasedStudentRepositoryTest {

    @BeforeEach
    void setUp() {
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
        //when
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(testPersonalData,testLevel);
        //then
    }

    @Test
    void readStudent() {
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