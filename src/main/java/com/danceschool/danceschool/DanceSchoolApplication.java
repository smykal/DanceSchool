package com.danceschool.danceschool;

import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.teacher.TeacherSkills;
import com.danceschool.danceschool.teacher.TeacherSkillsFemale;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DanceSchoolApplication {
	public static void main(String[] args) {
	    TeacherSkills teacherSkills01 = new TeacherSkillsFemale(new PersonalData(new PersonalDataName("Ania"),new PersonalDataSurname("Przybylska"), Sex.FEMALE,AdvanceLevel.EXPERT));
        TeacherSkills teacherSkills02 = new TeacherSkillsFemale(new PersonalData(new PersonalDataName("Sasha"),new PersonalDataSurname("Grey"),Sex.FEMALE,AdvanceLevel.MASTER));

        List<TeacherSkills> teacherSkillsFemaleList = new ArrayList<TeacherSkills>();
        teacherSkillsFemaleList.add(teacherSkills01);
        teacherSkillsFemaleList.add(teacherSkills02);

        for (TeacherSkills teacherSkills : teacherSkillsFemaleList) {
            System.out.println(teacherSkills.getPersonalName());
            System.out.println(teacherSkills.getPersonalSurname());
        }

        System.out.println(teacherSkills01.getPersonalSurname());

        Secretary maggy = new Secretary.SecretaryBuilder(Position.SECRETARY,"Magdalena", "Dora")
                .build();

        System.out.println(maggy);

        Map<String, PersonalData> newGroup = new HashMap<>();

	   // SpringApplication.run(DanceSchoolApplication.class, args);
	}

}
