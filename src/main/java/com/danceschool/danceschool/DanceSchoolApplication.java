package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Group;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        System.out.println(madzia.toString());
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"), Level.PROFFESIONAL).build();
        Teacher teacherMariusz = new Teacher.Builder(new PersonalData("Mariusz", "Tkacz"), Level.PROFFESIONAL).build();
        Teacher teacherJulia = new Teacher.Builder(new PersonalData("Julia", "Adamczyk"), Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Set<Student> studentSetMariusz = new HashSet<>();
        Set<Student> studentSetJulia = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR, teacherAdam, studentSetAdam);
        Group group02 = new Group(Level.AMATEUR, teacherMariusz, studentSetMariusz);
        Group group03 = new Group(Level.PROFFESIONAL, teacherJulia, studentSetJulia);
        System.out.println(studentSetAdam.getClass());
        System.out.println(teacherAdam.getClass().getSimpleName());
        // SpringApplication.run(DanceSchoolApplication.class, args);
    }
}
