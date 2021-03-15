package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.StudentRepository;
import com.danceschool.danceschool.teacher.Teacher;
import com.danceschool.danceschool.teacher.TeacherRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Secretary {
    private PersonalData personalData;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public Secretary(PersonalData personalData, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.personalData = personalData;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void registerStudent() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("give name: ");
            String registerStudentName = scanner.nextLine();
            System.out.println("give surname: ");
            String registerStudentSurname = scanner.nextLine();
            System.out.println("give level: ");
            String registerStudentLevel = scanner.nextLine();
            System.out.println("give address: ");
            String registerStudentAddress = scanner.nextLine();
            PersonalData personalData = createPersonalData(registerStudentName, registerStudentSurname, registerStudentAddress);
            Level level = Level.valueOf(registerStudentLevel);
            studentRepository.createStudent(personalData, level);
        } catch (IllegalArgumentException | IOException exception) {
            System.out.println("wrong kind of enum: " + exception.getMessage());
        }

    }

    private PersonalData createPersonalData(String registerStudentName, String registerStudentSurname, String registerStudentAddress) {
        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName(registerStudentName)
                .withSurname(registerStudentSurname)
                .withAddress(registerStudentAddress)
                .build();
        return personalData;
    }

    public void printStudentInfo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of student to show detais: ");
        String surname = scanner.nextLine();
        studentRepository.readStudent(surname);
    }

    public void updateStudentInfo() throws IOException {
        System.out.println("give surname of student to upgrade his data");
        Scanner scanner = new Scanner(System.in);
        String surname = scanner.nextLine();
        System.out.println("Wybrałeś studenta o nazwisku: " + surname);
        System.out.println("give new name");
        String newName = scanner.nextLine();
        System.out.println("give new surnamename");
        String newSurname = scanner.nextLine();
        System.out.println("give new adress");
        String newAddress = scanner.nextLine();
        System.out.println("give new level");
        String newLevelStudent = scanner.nextLine();
        PersonalData newPersonalData = createPersonalData(newName, newSurname, newAddress);
        Level newLevel = Level.valueOf(newLevelStudent);
        studentRepository.updateStudent(surname, personalData, newLevel);

    }

    public void deleteStudent() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of student to delete: ");
        String surname = scanner.nextLine();
        studentRepository.deleteStudent(surname);
    }

    public void registerTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give teacher name: ");
        String name = scanner.nextLine();
        System.out.println("give teacher surname: ");
        String surname = scanner.nextLine();
        System.out.println("give teacher address: ");
        String address = scanner.nextLine();
        System.out.println("give teacher level: ");
        String teacherLevel = scanner.nextLine();
        PersonalData personalData = createPersonalData(name, surname, address);
        Level level = Level.valueOf(teacherLevel);
        Teacher teacher = new Teacher.Builder()
                .personalData(personalData)
                .level(level)
                .build();
        teacherRepository.createTeacher(personalData, level);
    }

    public void printTeacherInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of teacher to show");
        String surname = scanner.nextLine();
        teacherRepository.readTeacher(surname);
    }

    public void updateTeacherInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of teacher whose data to change ");
        String surname = scanner.nextLine();
        System.out.println("give new name");
        String newName = scanner.nextLine();
        System.out.println("give new surname");
        String newSurname = scanner.nextLine();
        System.out.println("give new address");
        String newAddress = scanner.nextLine();
        System.out.println("give new level");
        String newLevel = scanner.nextLine();
        PersonalData personalData = createPersonalData(newName, newSurname, newAddress);
        Level level = Level.valueOf(newLevel);
        teacherRepository.updateTeacher(surname, personalData, level);
    }

    public void removeTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of teacher to remove");
        String surname = scanner.nextLine();
        teacherRepository.deleteTeacher(surname);
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }
}
