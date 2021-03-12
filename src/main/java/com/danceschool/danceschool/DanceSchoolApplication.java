package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.FileBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        FileBasedStudentRepository fileBasedStudentRepository = FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance();

        fileBasedStudentRepository.createNewStudentList();

        PersonalData personalData = createPersonalData(
                "testName", "testSurname", "testAddress");
        Level level = Level.PROFESSIONAL;
        PersonalData personalData1 = createPersonalData(
                "testName", "testSurname1", "testAddress");
        Level level1 = Level.PROFESSIONAL;
        PersonalData personalData2 = createPersonalData(
                "testName", "testSurname2", "testAddress");
        Level level2 = Level.PROFESSIONAL;
        PersonalData personalData3 = createPersonalData(
                "testName", "testSurname3", "testAddress");
        Level level3 = Level.PROFESSIONAL;

        fileBasedStudentRepository
                .createStudent(personalData, level);

        fileBasedStudentRepository
                .createStudent(personalData1, level1);

        fileBasedStudentRepository
                .createStudent(personalData2, level2);

        fileBasedStudentRepository
                .createStudent(personalData3, level3);

        fileBasedStudentRepository
                .readAllStudents();

        fileBasedStudentRepository
                .readStudent("testSurname2");

        PersonalData newPersonalData = createPersonalData(
                "randomName", "randomSurname", "randomAddress");
        fileBasedStudentRepository
                .updateStudent("testSurname2", newPersonalData, level);
//
//        FileBasedStudentRepository
//                .getFileBasedStudentRepositoryInstance()
//                .deleteStudent("testSurname3");

        // SpringApplication.run(DanceSchoolApplication.class, args);
    }

    private static PersonalData createPersonalData(
            String testName, String testSurname, String testAddress) {
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName(testName)
                .withSurname(testSurname)
                .withAddress(testAddress)
                .build();
        return personalData;
    }
}
