package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

public class FileBasedStudentRepository implements StudentRepository {

    private static final FileBasedStudentRepository FILE_BASED_STUDENT_REPOSITORY_INSTANCE =
            new FileBasedStudentRepository();

    private FileBasedStudentRepository() {
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
            String[] header = {">>name<<", ">>surname<<", ">>address<<", ">>level<<"};
            writer.writeNext(header);
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteNewCsvStudentList() throws IOException {
        try {
            Files.deleteIfExists(Paths.get(PATH));
        } catch (IOException error) {
            error.printStackTrace();
        }


    }

    @Override
    public void createStudent(PersonalData personalData, Level level) {
        try {
            FileWriter outputFile = new FileWriter(PATH, true);
            CSVWriter writer = new CSVWriter(outputFile);
            String[] student = {
                    personalData.getName(),
                    personalData.getSurname(),
                    personalData.getAddress(),
                    level.name()};
            writer.writeNext(student);
            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void readAllStudent() throws IOException {
        FileReader fileReader = new FileReader(PATH);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
    }

    @Override
    public void readStudent(String surname) {
        try (FileReader fileReader = new FileReader(PATH)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(surname)) {
                    System.out.println("\n" + "found student like: " + line + "\n");
                }
                line = reader.readLine();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void updateStudent(String surname, PersonalData newPersonalData, Level newLevel) {
        try (FileReader fileReader = new FileReader(PATH)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(surname)) {
                    System.out.println("found student like: " + line);
                    line.replace(surname, newPersonalData.getSurname());
                    System.out.println(line);
                }
                line = reader.readLine();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String surname) {

    }
}
