package com.danceschool.danceschool;

import com.danceschool.danceschool.teacher.Teacher;
import com.danceschool.danceschool.teacher.TeacherFemale;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DanceSchoolApplication {

	public static void main(String[] args) {
	    Teacher teacher01 = new TeacherFemale(new PersonalData(new PersonalDataName("Ania"),new PersonalDataSurname("Przybylska"), Sex.FEMALE,AdvanceLevel.EXPERT));
        Teacher teacher02 = new TeacherFemale(new PersonalData(new PersonalDataName("Sasha"),new PersonalDataSurname("Grey"),Sex.FEMALE,AdvanceLevel.MASTER));

        List<Teacher> teacherFemaleList = new ArrayList<Teacher>();
        teacherFemaleList.add(teacher01);
        teacherFemaleList.add(teacher02);

        for (Teacher teacher : teacherFemaleList) {
            System.out.println(teacher.getPersonalName());
            System.out.println(teacher.getPersonalSurname());
        }

        System.out.println(teacher01.getPersonalSurname());
	 //   SpringApplication.run(DanceSchoolApplication.class, args);
	}

}
