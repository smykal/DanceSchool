package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileBasedStudentRepository implements StudentRepository{

    public void createNewStudentList() throws IOException {
        String path = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";
        Files.createFile(Paths.get("C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv"));
        long fileName = Files.size(Paths.get("C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv"));
        System.out.println(fileName);
        boolean executable = Files.isExecutable(Paths.get("C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv"));
        System.out.println(executable);
    }

    public void deleteNewCsvStudentList() throws IOException {
        Files.deleteIfExists(Paths.get("C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv"));
    }


    private static final FileBasedStudentRepository FILE_BASED_STUDENT_REPOSITORY_INSTANCE =
            new FileBasedStudentRepository();
    private FileBasedStudentRepository(){
    }

    public static FileBasedStudentRepository getFileBasedStudentRepositoryInstance() {
        return FILE_BASED_STUDENT_REPOSITORY_INSTANCE;
    }


    @Override
    public void createStudent(PersonalData personalData, Level level) {

    }

    @Override
    public void readStudent(String surname) {

    }

    @Override
    public void updateStudent(String surname, PersonalData newPersonalData, Level newLevel) {

    }

    @Override
    public void deleteStudent(String surname) {

    }
}
