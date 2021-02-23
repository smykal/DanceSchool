package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.util.ArrayList;
import java.util.List;

public class MemoryBasedTeacherRepository implements TeacherRepository{
    private List<Teacher> teacherList = new ArrayList<>();
    private static final MemoryBasedTeacherRepository memoryBasedTeacherRepositoryInstance = new MemoryBasedTeacherRepository();
    private MemoryBasedTeacherRepository() {
    }
    public static MemoryBasedTeacherRepository getMemoryBasedTeacherRepositoryInstance(){
        return memoryBasedTeacherRepositoryInstance;
    }

    @Override
    public void createTeacher(PersonalData personalData, Level level) {
        Teacher teacher = new Teacher.Builder()
                .personalData(personalData)
                .level(level)
                .build();
        teacherList.add(teacher);
        System.out.println("Add element: " + teacher.getClass().getSimpleName());
        System.out.println(teacher.toString());

    }

    @Override
    public void readTeacher(String surname) {
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacher = teacherList.get(i);
            if (teacher.getSurname().equals(surname) == true) {
                System.out.println(teacher.toString());
            }
        }
    }

    @Override
    public void updateTeacher(String surname, PersonalData newPersonalData, Level newLevel) {
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacher = new Teacher.Builder()
                    .personalData(newPersonalData)
                    .level(newLevel)
                    .build();
            if (teacher.getSurname().equals(surname) == true) {
                teacherList.set(i,teacher);
            }
        }

    }

    @Override
    public void deleteTeacher(String surname) {
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacher = teacherList.get(i);
            if (teacher.getSurname().equals(surname) == true) {
                System.out.println("teacher for remove: " + teacher.getClass().getSimpleName());
                System.out.println("teacher for remove: " + teacher.toString());
                teacherList.remove(i);
            }
        }
    }
}
