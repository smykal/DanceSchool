package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.FileBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
            FileBasedStudentRepository
                    .getFileBasedStudentRepositoryInstance()
                    .createNewStudentList();


        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
