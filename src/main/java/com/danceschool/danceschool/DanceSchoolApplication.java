package com.danceschool.danceschool;

import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.student.MemoryBasedStudentRepository;
import com.danceschool.danceschool.teacher.MemoryBasedTeacherRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
        Secretary madzia = new Secretary(new PersonalData("Imie", "Nazwisko", "Adres"),
                MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance(),
                MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance());

        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
