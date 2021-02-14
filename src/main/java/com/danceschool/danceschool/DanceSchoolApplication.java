package com.danceschool.danceschool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.parameters.P;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
	public static void main(String[] args) {
        Secretary madzia = new Secretary.Builder(new PersonalData("Magdalena", "Manna")).build();
        System.out.println(madzia.toString());
        Teacher teacherAdam = new Teacher.Builder(new PersonalData("Adam", "Nalkowski"),Level.PROFFESIONAL).build();
        Teacher teacherMariusz = new Teacher.Builder(new PersonalData("Mariusz", "Tkacz"),Level.PROFFESIONAL).build();
        Teacher teacherJulia = new Teacher.Builder(new PersonalData("Julia", "Adamczyk"),Level.PROFFESIONAL).build();
        Set<Student> studentSetAdam = new HashSet<>();
        Set<Student> studentSetMariusz = new HashSet<>();
        Set<Student> studentSetJulia = new HashSet<>();
        Group group01 = new Group(Level.AMATEUR,teacherAdam,studentSetAdam);
        Group group02 = new Group(Level.AMATEUR,teacherMariusz,studentSetMariusz);
        Group group03 = new Group(Level.PROFFESIONAL,teacherJulia,studentSetJulia);

        madzia.showGroupMembers(group01);
        madzia.showGroupMembers(group02);
        madzia.showGroupMembers(group03);


        madzia.changeTeacher(group02,new Teacher.Builder(new PersonalData("Anna","Stec"),Level.PROFFESIONAL).build());

        madzia.showGroupMembers(group02);

        madzia.addStudent(group03,new Student.Builder(new PersonalData("Ania","Adamczyk"),Level.AMATEUR).build());

        madzia.showGroupMembers(group03);

        madzia.removeStudent(group03,new Student.Builder(new PersonalData("Ania","Adamczyk"),Level.AMATEUR).build());

        madzia.showGroupMembers(group03);

        // SpringApplication.run(DanceSchoolApplication.class, args);
	}


}
