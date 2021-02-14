package com.danceschool.danceschool;

import java.util.Set;

public class Group {
    Level level;
    Teacher teacher;
    Set<Student> studentSet;

    public Group(Level level, Teacher teacher, Set<Student> studentSet) {
        this.level = level;
        this.teacher = teacher;
        this.studentSet = studentSet;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
