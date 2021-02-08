package com.danceschool.danceschool;

import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.student.Trainee;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
	public static void main(String[] args) {
        Secretary madzia = new Secretary.SecretaryBuilder("Madzia", "Zorro").build();
        Set<Trainee> traineeSet = new HashSet<>();
        madzia.createStudent(new PersonalData("Krzysztof","Wielick"),new Address("Katowice obok hałd"),Level.HIGH);
	    Group group = new Group(madzia.createTeacher(new PersonalData("Arczi", "Nazwisko"), new Address("Warszawa, okolice Puławskiej")),traineeSet,Level.HIGH);
        group.addTrainee(new Trainee.TraineeBuilder(Level.LOW, new PersonalData("Anna","Wójcik"),new Address("Kraków, Czepca")).build());
        group.addTrainee(new Trainee.TraineeBuilder(Level.LOW, new PersonalData("Jolanta","Retinger"),new Address("Australia, westCoast")).build());
        group.addTrainee(new Trainee.TraineeBuilder(Level.LOW, new PersonalData("AngelaDora","NoviDeChavaria"),new Address("Italy, Naples")).build());

        System.out.println("Grupa 1");
        System.out.println(group.getTeacher().toString());
        for (Trainee trainee : traineeSet) {
            System.out.println(trainee);
        }
        // SpringApplication.run(DanceSchoolApplication.class, args);
	}


}
