package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MemoryBasedStudentRepository implements StudentRepository{
    private List<Student> studentList = new ArrayList<>();
    private static final MemoryBasedStudentRepository MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE =
            new MemoryBasedStudentRepository();
    private MemoryBasedStudentRepository(){
    }

    public static MemoryBasedStudentRepository getMemoryBasedStudentRepositoryInstance(){
        return MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE;
    }

    @Override
    public void createStudent(PersonalData personalData, Level level) throws IOException {
               Student student = new Student.Builder(personalData)
                       .level(level)
                       .build();
               studentList.add(student);
               System.out.println("add student: " + student.toString());
       }


    @Override
    public void readStudent(String surname) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            if (student.getSurname().equals(surname)) {
                System.out.println("show student data: " + student);
            }
        }
    }

    @Override
    public void updateStudent(String surname, PersonalData newPersonalData, Level newLevel) {
        Student newStudent = new Student.Builder(newPersonalData)
                .level(newLevel)
                .build();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getSurname().equals(surname)) {
                System.out.println("old student: " + student.toString());
                studentList.set(i,newStudent);
                System.out.println("new student: " + newStudent.toString());
            }
        }
    }

    @Override
    public void deleteStudent(String surname) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getSurname().equals(surname)) {
                System.out.println("Student to remove: " + student.toString());
                studentList.remove(i);
            }
        }
    }

    public List<Student> getStudentList() {
        List<Student> studentListCopy = new ArrayList<>();
        studentListCopy.addAll(studentList);
        return studentListCopy;
    }

    public void deleteAllStudents() {
        studentList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryBasedStudentRepository that = (MemoryBasedStudentRepository) o;

        return studentList != null ? studentList.equals(that.studentList) : that.studentList == null;
    }

    @Override
    public int hashCode() {
        return studentList != null ? studentList.hashCode() : 0;
    }
}
