package com.danceschool.danceschool.teacher;


import com.danceschool.danceschool.Address;
import com.danceschool.danceschool.Level;
import com.danceschool.danceschool.PersonalData;

public class Teacher {
    private final Level level;
    private final PersonalData personalData;
    private final Address address;
    
    public Teacher(TeacherBuilder builder) {
        this.level = builder.level;
        this.personalData = builder.personalData;
        this.address = builder.address;
    }

    public Level getLevel() {
        return level;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "level=" + level +
                ", personalData=" + personalData +
                ", address=" + address +
                '}';
    }

    public static class TeacherBuilder {
        private Level level;
        private PersonalData personalData;
        private Address address;

        public Teacher build() {
            Teacher teacher = new Teacher(this);
            return teacher;
        }

        public TeacherBuilder setLevel(Level level) {
            this.level = level;
            return this;
        }

        public TeacherBuilder setFirstName(String name) {
            this.personalData.setName(name);
            return this;
        }

        public TeacherBuilder setLastName(String lastName) {
            this.personalData.setSurname(lastName);
            return this;
        }

        public TeacherBuilder setAddress(String address) {
            this.address.setAddress(address);
            return this;
        }
    }
}
