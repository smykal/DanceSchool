package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.FileBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createNewStudentList();

        PersonalData personalData = new PersonalData(
                "testName1", "testSurname1,", "testAddress1");
        Level level = Level.PROFESSIONAL;
        PersonalData personalData1 = new PersonalData(
                "testName2", "testSurname2,", "testAddress2");
        Level level1 = Level.AMATEUR;
        PersonalData personalData2 = new PersonalData(
                "testName3", "testSurname3,", "testAddress3");
        Level level2 = Level.PROFESSIONAL;

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(personalData, level);

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(personalData1, level1);

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createStudent(personalData2, level2);

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .readAllStudent();

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .readStudent("testSurname2");

        PersonalData newPersonalData = new PersonalData(
                "newName", "newSurname", "newAddress");
        Level newLevel = Level.PROFESSIONAL;

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .updateStudent("testSurname2",newPersonalData,level);


        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
