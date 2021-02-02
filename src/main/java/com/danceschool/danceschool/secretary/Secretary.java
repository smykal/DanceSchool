package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.Address;
import com.danceschool.danceschool.Level;
import com.danceschool.danceschool.PersonalData;
import com.danceschool.danceschool.student.Trainee;
import com.danceschool.danceschool.teacher.Teacher;


public class Secretary implements SecretarySkills {
    private final String firstName;
    private final String lastName;

    private Secretary(SecretaryBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
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
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }

    @Override
    public Teacher createTeacher(PersonalData personalData, Address address) {
        Teacher teacher = new Teacher.TeacherBuilder(Level.HIGH, personalData, address)
                .setLevel(Level.HIGH)
                .setAddress(address.getAddress())
                .setLastName(personalData.getSurname())
                .setFirstName(personalData.getName())
                .build();
        return teacher;
    }

    @Override
    public void addTeacher() {
    }

    @Override
    public Trainee createStudent(PersonalData personalData, Address address, Level level) {
        Trainee trainee = new Trainee.TraineeBuilder(level, personalData, address)
                .setFirstName(personalData.getName())
                .setLastName(personalData.getSurname())
                .setAddress(address.getAddress())
                .setTraineeLevel(level)
                .build();
        return null;
    }

    @Override
    public void addStudent() {

    }

    public static class SecretaryBuilder {
        private final String firstName;
        private final String lastName;

        public SecretaryBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Secretary build() {
            Secretary secretary = new Secretary(this);
            return secretary;
        }
    }
}
