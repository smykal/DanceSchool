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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FileBasedStudentRepository implements StudentRepository {
    final String PATH = "C:/Users/Mateusz/IdeaProjects/DanceSchool/DanceSchool/csv/students.csv";
    public static final String NAME = ">>name<<";
    public static final String SURNAME = ">>surname<<";
    public static final String ADDRESS = ">>address<<";
    public static final String LEVEL = ">>level<<";
    public static final String ID = ">>student ID<<";

    private static final FileBasedStudentRepository FILE_BASED_STUDENT_REPOSITORY_INSTANCE =
            new FileBasedStudentRepository();

    private FileBasedStudentRepository() {
    }

    public static FileBasedStudentRepository getFileBasedStudentRepositoryInstance() {
        return FILE_BASED_STUDENT_REPOSITORY_INSTANCE;
    }

    @Override
    public UUID createStudent(PersonalData personalData, Level level) {
        CSVWriter writer = null;
        Student student = new Student
                .Builder()
                .personalData(personalData)
                .level(level)
                .build();
        String[] studentData = {
                personalData.getName(),
                personalData.getSurname(),
                personalData.getAddress(),
                level.name(),
                student.getId().toString()};
        try {
            FileWriter outputFile = new FileWriter(PATH, true);
            writer = new CSVWriter(outputFile);
            writer.writeNext(studentData, false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        finally {
            System.out.println("createStudent: " + Arrays.toString(studentData));
            closeCsvWriterResource(writer);
        }
        return student.getId();
    }

    @Override
    public Student readStudent(UUID uuid) {
        System.out.println("readStudent with id: " + uuid.toString());
        try (FileReader fileReader = new FileReader(PATH);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(uuid.toString())) {
                    System.out.println("\n" + "found student like: " + line + "\n");
                } else {
                    System.out.println("\n" + " no such student with id: "
                            + uuid.toString());
                }
                line = reader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Student updateStudent(
            UUID uuid,
            PersonalData newPersonalData,
            Level newLevel
    ) {
        System.out.println("updateStudent with id: " + uuid.toString());
        BufferedReader reader = null;
        List<Student> studentList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String ignoredTitleLine = reader.readLine();
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
            if (student.getId().equals(uuid)) {
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
        return null;
    }

    @Override
    public UUID deleteStudent(UUID uuid) throws IOException {
        System.out.println("deleteStudent:");
        List<String> studentList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains(uuid.toString())) {
                    studentList.add(line);
                    line = reader.readLine();
                }
            }
        } finally {
            closeCsvReader(reader);
        }

        CSVWriter writer = null;
        try {
            FileWriter inputWriter = new FileWriter(PATH);
            writer = new CSVWriter(inputWriter);

            for (String s : studentList) {
                String[] entries = s.split("#");
                writer.writeNext(entries);
            }
        } finally {
            closeCsvWriterResource(writer);
        }
        return null;
    }

    public void createNewStudentList() {
        System.out.println("createNewStudentList");
        File studentCSVFile = new File(PATH);

        CSVWriter writer = null;
        String[] header = {NAME, SURNAME, ADDRESS, LEVEL, ID};
        try {
            FileWriter outputFile = new FileWriter(studentCSVFile);
            writer = new CSVWriter(outputFile);
            writer.writeNext(header);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            closeCsvWriterResource(writer);
        }
    }

    public Student readAllStudents() throws IOException {
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
        return null;
    }

    public void deleteNewCsvStudentList() {
        System.out.println("deleteNewCsvStudentList");
        try {
            Files.deleteIfExists(Paths.get(PATH));
        } catch (IOException error) {
            System.out.println("such file doesnt exist ");
            error.printStackTrace();
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
