package com.danceschool.danceschool;

import com.danceschool.danceschool.teacher.TeacherFemale;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DanceSchoolApplication {

	public static void main(String[] args) {
        TeacherFemale anna = new TeacherFemale(new PersonalInformation("Anna", "Wciapkach"));
//		SpringApplication.run(DanceSchoolApplication.class, args);
        anna.getPersonalInformation().setName("Andrzej");
        System.out.println(anna.getPersonalName("sad"));
        System.out.println(anna.getPersonalName("name"));
  //      System.out.println(anna.getPersonalInformation().getName());

	}

}
