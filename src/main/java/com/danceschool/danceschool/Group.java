package com.danceschool.danceschool;

import com.danceschool.danceschool.student.Trainee;
import com.danceschool.danceschool.teacher.Teacher;

import java.util.Set;

public class Group {
    private Teacher teacher;
    private Set<Trainee> trainees;
    private Level level;

    public Group(Teacher teacher, Set<Trainee> trainees, Level level) {
        this.teacher = teacher;
        this.trainees = trainees;
        this.level = level;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Group{" +
                "teacher=" + teacher +
                ", trainees=" + trainees +
                ", level=" + level +
                '}';
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }
}
