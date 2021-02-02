package com.danceschool.danceschool;

import com.danceschool.danceschool.secretary.Secretary;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
	public static void main(String[] args) {
        Secretary madzia = new Secretary.SecretaryBuilder("Madzia", "Zorro").build();
        madzia.createTeacher(new PersonalData("Lukasz", "Wawko"),new Address("30-812 Kraków, Wielicka 123"));
//        madzia.createStudent(new PersonalData("Andrzej","Bargiel"),new Address("Zakopane tuż obok Kościeliska 225"),Level.HIGH);
//
//        madzia.createStudent(new PersonalData("Tomasz","Mackiewicz"),new Address("Sosnowiec gdzie trzeba mieć paszport"),Level.HIGH);
//        madzia.createStudent(new PersonalData("Marek","Kaim"),new Address("Podhale"),Level.LOW);
	   // SpringApplication.run(DanceSchoolApplication.class, args);
        System.out.println(madzia.toString());
        System.out.println(madzia.createStudent(new PersonalData("Krzysztof","Wielick"),new Address("Katowice obok hałd"),Level.HIGH));
	}

}
