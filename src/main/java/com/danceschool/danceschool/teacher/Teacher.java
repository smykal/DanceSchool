package com.danceschool.danceschool.teacher;


import com.danceschool.danceschool.Position;

public class Teacher {
    private final Position position;
    private final String firstName;
    private final String lastName;

    public Teacher(TeacherBuilder builder) {
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
        return "Teacher{" +
                "position=" + position +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class TeacherBuilder{
        private final Position position;
        private final String firstName;
        private final String lastName;

        public TeacherBuilder(Position position, String firstName, String lastName) {
            this.position = position;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Teacher build(){
            Teacher teacher = new Teacher(this);
            return teacher;
        }
    }
}
