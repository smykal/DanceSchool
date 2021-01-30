package com.danceschool.danceschool;

import com.danceschool.danceschool.secretary.Secretary;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DanceSchoolApplication {
	public static void main(String[] args) {
        Set<Object> groupMonday = new HashSet<>();
        Secretary maggy = new Secretary.SecretaryBuilder(Position.SECRETARY,"Magdalena", "Dora")
                .build();
        Secretary ann = new Secretary.SecretaryBuilder(Position.SECRETARY,"Anna", "Paw")
                .build();

        groupMonday.add(maggy);
        groupMonday.add(maggy.addTeacher());
        groupMonday.add(maggy.addStudent());

        Iterator<Object> groupMondayIterator = groupMonday.iterator();

        while(groupMondayIterator.hasNext()){
            System.out.println(groupMondayIterator.next());
        }



	   // SpringApplication.run(DanceSchoolApplication.class, args);
	}

}
