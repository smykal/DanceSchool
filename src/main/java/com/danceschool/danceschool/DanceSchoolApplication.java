package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Group;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.student.MemoryBasedStudentRepository;
import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
        Secretary madzia = new Secretary(new PersonalData("Imie", "Nazwisko", "Adres"),
                new MemoryBasedStudentRepository());
        madzia.registerStudent();
        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
