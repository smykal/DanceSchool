package com.danceschool.danceschool;

import com.danceschool.danceschool.student.FileBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        try {
            FileBasedStudentRepository
                    .getFileBasedStudentRepositoryInstance()
                    .createNewStudentList();

            FileBasedStudentRepository
                    .getFileBasedStudentRepositoryInstance()
                    .deleteNewCsvStudentList();
        } catch (IOException exception) {
            System.out.println(exception);
        }


        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
