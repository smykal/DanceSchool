package com.danceschool.danceschool.student;

import com.danceschool.danceschool.Address;
import com.danceschool.danceschool.Level;
import com.danceschool.danceschool.PersonalData;

public class Trainee {
    private final Level level;
    private final PersonalData personalData;
    private final Address address;

    public Trainee(TraineeBuilder traineeBuilder) {
        this.level = traineeBuilder.level;
        this.personalData = traineeBuilder.personalData;
        this.address = traineeBuilder.address;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "level=" + level +
                ", personalData=" + personalData.getName() + " " + personalData.getSurname() +
                ", address=" + address.getAddress() +
                '}';
    }

    public static class TraineeBuilder {
        private Level level;
        private PersonalData personalData;
        private Address address;

        public TraineeBuilder(Level level, PersonalData personalData, Address address) {
            this.level = level;
            this.personalData = personalData;
            this.address = address;
        }

        public Trainee build() {
            Trainee trainee = new Trainee(this);
            return trainee;
        }

        public TraineeBuilder setTraineeLevel(Level level) {
            this.level = level;
            return this;
        }

        public TraineeBuilder setFirstName(String name) {
            this.personalData.setName(name);
            return this;
        }

        public TraineeBuilder setLastName(String surname) {
            this.personalData.setSurname(surname);
            return this;
        }

        public TraineeBuilder setAddress(String address) {
            this.address.setAddress(address);
            return this;
        }
    }
}
