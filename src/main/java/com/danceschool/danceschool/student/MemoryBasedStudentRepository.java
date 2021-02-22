package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.util.ArrayList;
import java.util.List;

public class MemoryBasedStudentRepository implements StudentRepository{
    private List<Student> studentList = new ArrayList<>();

    @Override
    public void createStudent(PersonalData personalData, Level level) {
        Student student = new Student.Builder(personalData)
                .level(level)
                .build();
        studentList.add(student);
        System.out.println("add student: " + student.toString());
    }

    @Override
    public Student readStudent(String surname) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            if (student.getSurname().equals(surname) == true) {
                return student;
            }
        }
        throw new IllegalStateException("Not found student with surname: " + surname);
    }

    @Override
    public void updateStudent(PersonalData oldPersonalData, PersonalData newPersonalData, Level newLevel) {
//        for (int i = 0; i < studentList.size(); i++) {
//            Student student = studentList.get(i);
//
//            if (student.getSurname().equals(surname) == true) {
//                studentList.set(i,student);
//            }
//        }
    }

    @Override
    public void deleteStudent(String surname) {

    }
}
