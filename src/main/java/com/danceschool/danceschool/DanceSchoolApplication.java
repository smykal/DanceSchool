package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.groups.Groups;
import com.danceschool.danceschool.schools.School;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.members.student.MemoryBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        School getSchools = School.getSchoolListInstance();
                getSchools.iterateSchools(getSchools.getSchoolsList());

        MemoryBasedStudentRepository memoryBasedStudentRepository = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance();
        Groups getGroups = Groups.getDanceSchoolGroups();

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(new PersonalData.PersonalDataBuilder()
                        .withName("Anna")
                        .withSurname("Nowak")
                        .withAddress(new Address.Builder()
                                    .city("Kraków")
                                    .postalCode("30-001")
                                    .street("Grodzka")
                                    .blockNumber("43")
                                    .apartmentNumber("2")
                                    .build())
                        .build(), Level.PROFESSIONAL);

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(new PersonalData.PersonalDataBuilder()
                        .withName("Jakub")
                        .withSurname("Gzyl")
                        .withAddress(new Address.Builder()
                                .city("Warszawa")
                                .postalCode("01-021")
                                .street("Krakowska")
                                .blockNumber("3")
                                .apartmentNumber("21")
                                .build())
                        .build(), Level.PROFESSIONAL);

        Secretary madzia = new Secretary.SecretaryBuilder()
                .withPersonalData(new PersonalData.PersonalDataBuilder()
                        .withName("Madzia")
                        .withSurname("Ziarko")
                        .withAddress(new Address.Builder()
                                .city("Kielce")
                                .postalCode("22-052")
                                .street("Rydza")
                                .blockNumber("2")
                                .build())
                        .build())
                .build();

        madzia.setUpNewGroup("Nowa Grupa");
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-912")
                .street("Długa")
                .blockNumber("12")
                .build();

        madzia.addSchool("Sabrosa", new Address.Builder()
                                                    .city("Kraków")
                                                    .postalCode("00-001")
                                                    .street("Rondo Mogilskie")
                                                    .blockNumber("12")
                                                    .apartmentNumber("1")
                                                    .build());

        System.out.println(School.getSchoolListInstance().iterateSchools(getSchools.getSchoolsList()));

    }
}
