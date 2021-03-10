package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

public class FileBasedStudentRepository implements StudentRepository{

    private static final FileBasedStudentRepository FILE_BASED_STUDENT_REPOSITORY_INSTANCE =
            new FileBasedStudentRepository();
    private FileBasedStudentRepository(){
    }

    public static FileBasedStudentRepository getFileBasedStudentRepositoryInstance() {
        return FILE_BASED_STUDENT_REPOSITORY_INSTANCE;
    }
    final String PATH = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";

    public void createNewStudentList() {
        File studentCSVFile = new File(PATH);
        try {
            FileWriter outputFile = new FileWriter(studentCSVFile);
            CSVWriter writer = new CSVWriter(outputFile);
            String[] header = {"name", "surname",  "address", "level"};
            writer.writeNext(header);

            //add example data to studentCSVFile
            String[] data1 = {"name1", "suranem1", "address1", "level1"};
            String[] data2 = {"name2", "suranem2", "address2", "level2"};
            writer.writeNext(data1);
            writer.writeNext(data2);

            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void deleteNewCsvStudentList() throws IOException {
        Files.deleteIfExists(Paths.get(PATH));
    }

    @Override
    public void createStudent(PersonalData personalData, Level level) throws IOException {
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
