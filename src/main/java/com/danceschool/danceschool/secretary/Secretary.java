package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.StudentRepository;
import com.danceschool.danceschool.teacher.Teacher;
import com.danceschool.danceschool.teacher.TeacherRepository;

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
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz: ");
        System.out.println("1 - aby zarządzać studentami/studentkami");
        System.out.println("2 - aby pewnie pozwalniać belfrów");
        System.out.println("0 - aby zakończyć");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                crudForStudent();
                break;
            case "2":
                crudForTeacher();
                break;
            case "0":
                System.out.println("Fin");
                break;
            default:
                System.out.println("Wszystko źle, z tego nic nie będzie");
        }
    }
    public void crudForStudent() {
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
                printStudentInfo();
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
    public void crudForTeacher() {
        System.out.println("Wybierz cyfrę: 1, 2, 3 lub 4 aby odpowiednio:");
        System.out.println("1 - dodaj nowego nauczyciela");
        System.out.println("2 - pokaż informacje o nauczycielu");
        System.out.println("3 - aktualizuj informacje o nauczycielu");
        System.out.println("4 - usuń belfra");
        Scanner scanner = new Scanner(System.in);
        String chooseAct = scanner.nextLine();
        switch (chooseAct) {
            case "1":
                registerTeacher();
                break;
            case "2":
                printTeacherInfo();
                break;
            case "3":
                updateTeacherInfo();
                break;
            case "4":
                removeTeacher();
                break;
            default:
                System.out.println("you choose badly");
        }
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
            PersonalData personalData = new PersonalData(registerStudentName,registerStudentSurname,registerStudentAddress);
            Level level = Level.valueOf(registerStudentLevel);
            studentRepository.createStudent(personalData, level);
        } catch (IllegalArgumentException exception) {
            System.out.println("Nieprawidłowy rodzaj Enuma: " + exception.getMessage());
        }

    }

    public void printStudentInfo() {
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
        PersonalData personalData = new PersonalData(name,surname,address);
        Level level = Level.valueOf(teacherLevel);
        Teacher teacher = new Teacher.Builder()
                .personalData(personalData)
                .level(level)
                .build();
        teacherRepository.createTeacher(personalData,level);
    }
    public void printTeacherInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("give surname of teacher to show");
        String surname = scanner.nextLine();
        teacherRepository.readTeacher(surname);
    }

    public void updateTeacherInfo(){
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
        PersonalData personalData = new PersonalData(newName,newSurname,newAddress);
        Level level = Level.valueOf(newLevel);
        teacherRepository.updateTeacher(surname,personalData,level);
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
