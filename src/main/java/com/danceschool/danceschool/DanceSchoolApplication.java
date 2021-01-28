package com.danceschool.danceschool;

import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.teacher.Teacher;
import com.danceschool.danceschool.teacher.TeacherFemale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Secretary maggy = new Secretary.SecretaryBuilder(Position.SECRETARY,"Magdalena", "Dora")
                .build();

        System.out.println(maggy);

        Map<String, PersonalData> newGroup = new HashMap<>();

	   // SpringApplication.run(DanceSchoolApplication.class, args);
	}

}
