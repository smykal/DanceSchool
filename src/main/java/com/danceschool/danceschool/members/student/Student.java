package com.danceschool.danceschool.members.student;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Student extends Members {
    private PersonalData personalData;
    private Level level;
    private UUID id;

    public String getSurname() {
        return personalData.getSurname();
    }

    public String getName() {
        return personalData.getName();
    }

    public String getCity() {
        return personalData.getAddress().getCity();
    }

    public String getPostalCode() {
        return personalData.getAddress().getPostalCode();
    }

    public String getStreet() {
        return personalData.getAddress().getStreet();
    }

    public String getBlockNumber() {
        return personalData.getAddress().getBlockNumber();
    }

    public String getApartmentNumber() {
        if (personalData.getAddress().getApartmentNumber() == null) {
            return "none";
        } else {
            return personalData.getAddress().getApartmentNumber();
        }
    }

    public Level getLevel() {
        return level;
    }

    public UUID getId() { return id; }

    public void setName(String newName) { this.personalData.setName(newName); }
    public void setSurname(String newSurname) {
        this.personalData.setSurname(newSurname);
    }
    public void setAddress(Address newAddress) { this.personalData.setAddress(newAddress); }
    public void setLevel(Level level) { this.level = level; }
    public void setId(UUID id) { this.id = id; }

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
        this.id = UUID.randomUUID();
    }

    public String[] convertStudentToCsvFormat() {
        String[] studentArray = {getName(), getSurname(), getCity(), getPostalCode(),
                 getStreet(), getBlockNumber(), getApartmentNumber(),
                 getLevel().toString(), String.valueOf(getId())};
        return studentArray;
    }

    public static Student convertCsvStudentToStudent(String studentAsString) {
        List<String> studentAsArray = Arrays.asList(studentAsString.split(","));
        PersonalData.PersonalDataBuilder personalDataBuilder = new PersonalData.PersonalDataBuilder();
        if (studentAsArray != null) {
            PersonalData studentPersonalData = personalDataBuilder
                    .withName(studentAsArray.get(0))
                    .withSurname(studentAsArray.get(1))
                    .withAddress(new Address.Builder()
                                    .city(studentAsArray.get(2))
                                    .postalCode(studentAsArray.get(3))
                                    .street(studentAsArray.get(4))
                                    .blockNumber(studentAsArray.get(5))
                                    .apartmentNumber(studentAsArray.get(6))
                                    .build())
                    .build();
            Level level = Level.valueOf(studentAsArray.get(7).toUpperCase());
            Student student = new Builder()
                    .personalData(studentPersonalData)
                    .level(level)
                    .build();
            student.setId(UUID.fromString(studentAsArray.get(8)));
            return student;
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
                " Id= " + id.toString() +
                '}';
    }
}
