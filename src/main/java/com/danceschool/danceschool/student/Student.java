package com.danceschool.danceschool.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.List;

public class Student {
    private PersonalData personalData;
    private Level level;

    public String getSurname() {
        return personalData.getSurname();
    }

    public String getName() {
        return personalData.getName();
    }

    public String getAddress() {
        return personalData.getAddress();
    }

    public Level getLevel() {
        return level;
    }

    public void setName(String newName) {
        this.personalData.setName(newName); }

    public void setSurname(String newSurname) {
        this.personalData.setSurname(newSurname);
    }
    public void setAddress(String newAddress) {
        this.personalData.setAddress(newAddress);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public static class Builder {

        private PersonalData personalData;
        private Level level;

        public Builder() {
        }

        public Builder(PersonalData personalData) {
            this.personalData = personalData;
        }

        public Builder personalData(PersonalData personalData) {
            this.personalData = personalData;
            return Builder.this;
        }

        public Builder level(Level level) {
            this.level = level;
            return Builder.this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(Builder builder) {
        this.personalData = builder.personalData;
        this.level = builder.level;
    }

    public String[] convertStudentToCsvFormat() {
        String[] studentArray = {getName(), getSurname(), getAddress(), getLevel().toString()};
        return studentArray;
    }

    public static Student convertCsvStudentToStudent(String studentAsString) {
        List<String> studentAsArray = Arrays.asList(studentAsString.split(","));
        PersonalData.PersonalDataBuilder personalDataBuilder = new PersonalData.PersonalDataBuilder();
        if (studentAsArray != null) {
            PersonalData studentPersonalData = personalDataBuilder
                    .withName(studentAsArray.get(0))
                    .withSurname(studentAsArray.get(1))
                    .withAddress(studentAsArray.get(2))
                    .build();
            Level level = Level.valueOf(studentAsArray.get(3));
            return new Builder()
                    .personalData(studentPersonalData)
                    .level(level)
                    .build();
        }
        throw new IllegalStateException("unable to deserialize student");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (personalData != null ? !personalData.equals(student.personalData) : student.personalData != null)
            return false;
        return level == student.level;
    }

    @Override
    public int hashCode() {
        int result = personalData != null ? personalData.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "personalData=" + personalData +
                ", level=" + level +
                '}';
    }
}
