package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.groups.Groups;
import com.danceschool.danceschool.groups.MemoryBasedGroupRepository;
import com.danceschool.danceschool.schools.MemoryBasedSchoolRepository;
import com.danceschool.danceschool.secretary.Secretary;
import com.danceschool.danceschool.members.student.MemoryBasedStudentRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool("Sabrosa", new Address.Builder()
                                                        .city("Kraków")
                                                        .postalCode("00-001")
                                                        .street("Rondo Mogilskie")
                                                        .blockNumber("12")
                                                        .apartmentNumber("1")
                                                        .build());
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool("Salsa House", new Address.Builder()
                                                        .city("Kraków")
                                                        .postalCode("12-001")
                                                        .street("Długa")
                                                        .blockNumber("2")
                                                        .apartmentNumber("7")
                                                        .build());
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool("Forum", new Address.Builder()
                                                        .city("Kraków")
                                                        .postalCode("30-818")
                                                        .street("Nad Wisłą")
                                                        .blockNumber("Forum")
                                                        .apartmentNumber("Parter")
                                                        .build());

        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .deleteSchool("Sabrosa");

        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .readSchool("Forum");

        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .updateSchool("Salsa House",new Address.Builder()
                        .city("Kraków")
                        .postalCode("31-818")
                        .street("Dolne Młyny")
                        .blockNumber("15")
                        .apartmentNumber("5")
                        .build() );

        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("P1");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("P2");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("P3");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("S1");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("S2");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("S3");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("Z1");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("Z2");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("Z3");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup("Z4");
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .iterateGroups(MemoryBasedGroupRepository
                        .getMemoryBasedGroupRepositoryInstance()
                        .getGroups());

        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .readGroup("P2");



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

        Groups.getDanceSchoolGroups().iterateGroup("P1");

    }
}
