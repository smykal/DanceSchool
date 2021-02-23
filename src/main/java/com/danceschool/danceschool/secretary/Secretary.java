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
    public void chooseAct() {
        System.out.println("Wybierz cyfrę: 1, 2, 3 lub 4 aby odpowiednio:");
        System.out.println("1 - dodaj nowego studenta");
        System.out.println("2 - pokaż informacje o studnecie");
        System.out.println("3 - aktualizuj informacje o studnecie");
        System.out.println("4 - usuń studnenta");
        Scanner scanner = new Scanner(System.in);
        String chooseAct = scanner.nextLine();
        switch (chooseAct) {
            case "1":
                registerStudent();
                break;
            case "2":
                printStudnetInfo();
                break;
            case "3":
                updateStudentInfo();
                break;
            case "4":
                deleteStudent();
                break;
            default:
                System.out.println("you choose badly");
        }
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

    public void printStudnetInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of student to show detais: ");
        String surname = scanner.nextLine();
        studentRepository.readStudent(surname);
    }
    public void updateStudentInfo(){
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
        PersonalData newPersonalData = new PersonalData(newName,newSurname,newAddress);
        Level newLevel = Level.valueOf(newLevelStudent);
        studentRepository.updateStudent(surname,personalData,newLevel);

    }
    public void deleteStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of student to delete: ");
        String surname = scanner.nextLine();
        studentRepository.deleteStudent(surname);
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }
}
