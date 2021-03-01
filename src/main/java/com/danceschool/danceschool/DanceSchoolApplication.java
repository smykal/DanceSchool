package com.danceschool.danceschool;

import com.danceschool.danceschool.student.FileBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
//        Secretary madzia = new Secretary(new PersonalData("Imie", "Nazwisko", "Adres"),
//                MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance(),
//                MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance());
        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .createNewStudentList();

        FileBasedStudentRepository
                .getFileBasedStudentRepositoryInstance()
                .deleteNewCsvStudentList();

        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
