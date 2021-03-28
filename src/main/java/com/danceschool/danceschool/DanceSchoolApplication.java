package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.FileBasedStudentRepository;
import com.danceschool.danceschool.student.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.UUID;

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

        Student student = new Student
                .Builder()
                .level(Level.PROFESSIONAL)
                .personalData(personalData)
                .build();
        System.out.println("student ID");
        System.out.println(student.getId());


        fileBasedStudentRepository
                .readStudent(UUID.fromString("1bf58258-8e20-476c-984d-508e2e0083f3"));

//        PersonalData newPersonalData = createPersonalData(
//                "randomName", "randomSurname", "randomAddress");
//        fileBasedStudentRepository
//                .updateStudent("testSurname2", newPersonalData, level);
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
