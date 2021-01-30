package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.Position;
import com.danceschool.danceschool.student.Trainee;
import com.danceschool.danceschool.teacher.Teacher;


public class Secretary implements SecretarySkills {
    private final Position position;
    private final String firstName;
    private final String lastName;

    private Secretary(SecretaryBuilder builder) {
        this.position = builder.position;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public Position getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Secretary: {" +
                "position = " + position +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }

    @Override
    public Teacher addTeacher() {
        Teacher teacherBob = new Teacher.TeacherBuilder(Position.TEACHER, "Bob", "Dylan")
                .build();
        return teacherBob;
    }

    @Override
    public Trainee addStudent() {
            Trainee traineeMark = new Trainee.TraineeBuilder(Position.STUDENT,"Mark", "Zuckenburg")
                    .build();
        return traineeMark;
    }

    public static class SecretaryBuilder {
        private final Position position;
        private final String firstName;
        private final String lastName;

        public SecretaryBuilder(Position position, String firstName, String lastName) {
            this.position = position;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Secretary build() {
            Secretary secretary = new Secretary(this);
            return secretary;
        }
    }
}
