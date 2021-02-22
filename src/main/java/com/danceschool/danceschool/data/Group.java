package com.danceschool.danceschool.data;

import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;

import java.util.Set;

public class Group {
    private Level level;
    private Teacher teacher;
    private Set<Student> studentSet;

    public Group(Level level, Teacher teacher, Set<Student> studentSet) {
        this.level = level;
        this.teacher = teacher;
        this.studentSet = studentSet;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Level getLevel() {
        return level;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }
}
