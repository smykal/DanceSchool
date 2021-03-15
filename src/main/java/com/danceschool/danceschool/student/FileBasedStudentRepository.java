package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileBasedStudentRepository implements StudentRepository {

    private static final FileBasedStudentRepository FILE_BASED_STUDENT_REPOSITORY_INSTANCE =
            new FileBasedStudentRepository();

    private FileBasedStudentRepository() {
    }

    public static FileBasedStudentRepository getFileBasedStudentRepositoryInstance() {
        return FILE_BASED_STUDENT_REPOSITORY_INSTANCE;
    }

    final String PATH = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";

    public void createNewStudentList() throws IOException {
        System.out.println("createNewStudentList");
        File studentCSVFile = new File(PATH);
        final String NAME = ">>name<<";
        final String SURNAME = ">>surname<<";
        final String ADDRESS = ">>address<<";
        final String LEVEL = ">>level<<";
        CSVWriter writer = null;
        try {
            FileWriter outputFile = new FileWriter(studentCSVFile);
            writer = new CSVWriter(outputFile);
            String[] header = {NAME, SURNAME, ADDRESS, LEVEL};
            writer.writeNext(header);

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeCsvWriterResource(writer);
        }
    }

    public void deleteNewCsvStudentList() throws IOException {
        System.out.println("deleteNewCsvStudentList");
        try {
            Files.deleteIfExists(Paths.get(PATH));
        } catch (IOException error) {
            System.out.println("such file doesnt exist ");
            error.printStackTrace();
        }
    }

    @Override
    public void createStudent(PersonalData personalData, Level level) throws IOException {
       CSVWriter writer = null;
        try {
            FileWriter outputFile = new FileWriter(PATH, true);
            writer = new CSVWriter(outputFile);
            String[] student = {
                    personalData.getName(),
                    personalData.getSurname(),
                    personalData.getAddress(),
                    level.name()};
            writer.writeNext(student, false);

            System.out.println("createStudent: " + student.toString());
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeCsvWriterResource(writer);
        }
    }

    public void readAllStudents() throws IOException {
        BufferedReader reader = null;
        try {
            System.out.println("readAllStudent");
            FileReader fileReader = new FileReader(PATH);
            reader = new BufferedReader(fileReader);
            String line;
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } finally {
            closeCsvReader(reader);
        }
    }

    @Override
    public void readStudent(String surname) throws IOException {
        System.out.println("readStudent");
        BufferedReader reader = null;
        try (FileReader fileReader = new FileReader(PATH)) {
            reader = new BufferedReader(fileReader);
            String line;
            line = reader.readLine();
            while (line != null) {
                if (line.contains(surname)) {
                    System.out.println("\n" + "found student like: " + line + "\n");
                }
                line = reader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeCsvReader(reader);
        }
    }

    @Override
    public void updateStudent(
            String surname,
            PersonalData newPersonalData,
            Level newLevel
    ) {
        System.out.println("updateStudent:");
        BufferedReader reader = null;
        List<Student> studentList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String titleLine = reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                studentList.add(Student.convertCsvStudentToStudent(line));
                line = reader.readLine();
            }
        } catch (IOException exceptionXd) {
            exceptionXd.printStackTrace();
        } finally {
            closeCsvReader(reader);
        }

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getSurname().equalsIgnoreCase(surname)) {
                student.setName(newPersonalData.getName());
                student.setSurname(newPersonalData.getSurname());
                student.setAddress(newPersonalData.getAddress());
                student.setLevel(newLevel);
                studentList.set(i, student);
            }
        }

        FileWriter inputWriter = null;
        CSVWriter writer = null;
        try {
            inputWriter = new FileWriter(PATH);
            writer = new CSVWriter(inputWriter);
            for (Student student : studentList) {
                writer.writeNext(student.convertStudentToCsvFormat());
            }
        } catch (IOException wrongPath) {
            wrongPath.printStackTrace();
        } finally {
            closeCsvWriterResource(writer);
            closeResource(inputWriter);
        }

        System.out.println("początek wyświetlania listy");
        for (Student item : studentList) {
            System.out.println(item);
        }
    }



    @Override
    public void deleteStudent(String surname) throws IOException {
        System.out.println("deleteStudent:");
        List<String> studentList = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
            studentList = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                studentList.add(line);
                line = reader.readLine();
            }
        } finally {
            closeCsvReader(reader);
        }

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).contains(surname)) {
                studentList.remove(i);
            }
        }

        CSVWriter writer = null;
        try {
            FileWriter inputWriter = new FileWriter(PATH);
            writer = new CSVWriter(inputWriter);

            for (String s : studentList) {
                String[] entries = s.split("-");
                writer.writeNext(entries);
            }
        } finally {
            assert writer != null;
            writer.close();
        }

        System.out.println("początek wyświetlania listy");
        for (String item : studentList) {
            System.out.println(item);
        }
    }

    private void closeCsvReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResource(FileWriter inputWriter) {
        if (inputWriter != null) {
            try {
                inputWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeCsvWriterResource(CSVWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
