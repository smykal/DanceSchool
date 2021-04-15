package com.danceschool.danceschool.data;

import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group {
    String name;
    List<Teacher> teacherList = new ArrayList<>(1);
    List<Student> studentList = new ArrayList<>(11);
    public Group() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public String iteratorTeacherList() {
        StringBuilder result = new StringBuilder();
        result.append("grupa o nazwie: ").append(name);
        Iterator<Teacher> iterator = teacherList.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().toString());
        }
        return result.toString();
    }

    public String iteratorStudentList() {
        StringBuilder result = new StringBuilder();
        result.append("grupa o nazwie: ").append(name);
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().toString());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", teacherList=" + iteratorTeacherList() +
                ", studentList=" + iteratorStudentList() +
                '}';
    }
}

