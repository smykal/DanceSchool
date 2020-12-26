package com.danceschool.danceschool;

import com.danceschool.danceschool.teacher.Teacher;
import com.danceschool.danceschool.teacher.TeacherFemale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DanceSchoolApplication {

	public static void main(String[] args) {
	    TeacherFemale teacher01 = new TeacherFemale(new PersonalData("Ania","Przybylska", Sex.FEMALE,AdvanceLevel.EXPERT));
        TeacherFemale teacher02 = new TeacherFemale(new PersonalData("Sasha","Grey",Sex.FEMALE,AdvanceLevel.MASTER));

        List<TeacherFemale> teacherFemaleList = new ArrayList<TeacherFemale>();
        teacherFemaleList.add(teacher01);
        teacherFemaleList.add(teacher02);

        for (TeacherFemale teacherFemale : teacherFemaleList) {
            System.out.println(teacherFemale.getPersonalData().getName() + " " +  teacherFemale.getPersonalData().getSurname()
            + " " + teacherFemale.getPersonalData().getSex() + " " + teacherFemale.getPersonalData().getAdvanceLevel());
        }

        System.out.println(teacher01.getPersonalData().getAdvanceLevel());
	 //   SpringApplication.run(DanceSchoolApplication.class, args);
	}

}
