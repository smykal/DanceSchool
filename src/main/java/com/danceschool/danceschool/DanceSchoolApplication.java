package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.FileBasedStudentRepository;
import com.danceschool.danceschool.student.MemoryBasedStudentRepository;
import com.danceschool.danceschool.student.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) throws IOException {
        MemoryBasedStudentRepository memoryBasedStudentRepository = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance();

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(new PersonalData.PersonalDataBuilder()
                        .withName("Anna")
                        .withSurname("Nowak")
                        .withAddress("Kraków, Gdziekolwiek 15")
                        .build(), Level.PROFESSIONAL);

        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(new PersonalData.PersonalDataBuilder()
                        .withName("Jakub")
                        .withSurname("Gzyl")
                        .withAddress("Wieliczka, Street 12")
                        .build(), Level.PROFESSIONAL);

    }
}
