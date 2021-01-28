package com.danceschool.danceschool.student;

import com.danceschool.danceschool.Position;

public class Trainee {
    private final Position position;
    private final String firstName;
    private final String lastName;

    public Trainee(TraineeBuilder builder) {
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
        return "Trainee{" +
                "position=" + position +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class TraineeBuilder{
        private final Position position;
        private final String firstName;
        private final String lastName;

        public TraineeBuilder(Position position, String firstName, String lastName) {
            this.position = position;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Trainee build(){
            Trainee trainee = new Trainee(this);
            return trainee;
        }
    }
}
