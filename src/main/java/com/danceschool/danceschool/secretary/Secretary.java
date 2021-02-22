package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.StudentRepository;

import java.util.Scanner;

public class Secretary {
    private PersonalData personalData;
    private StudentRepository studentRepository;

    public Secretary(PersonalData personalData, StudentRepository studentRepository) {
        this.personalData = personalData;
        this.studentRepository = studentRepository;
    }
    public void registerStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give name: ");
        String registerStudentName = scanner.nextLine();
        System.out.println("give surname: ");
        String registerStudentSurname = scanner.nextLine();
        System.out.println("give level: ");
        String registerStudentLevel = scanner.nextLine();
        System.out.println("give address: ");
        String registerStudentAddress = scanner.nextLine();
        PersonalData personalData = new PersonalData(registerStudentName,registerStudentSurname,registerStudentAddress);
        Level level = Level.valueOf(registerStudentLevel);
        studentRepository.createStudent(personalData, level);

    }

    @Override
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }
}
