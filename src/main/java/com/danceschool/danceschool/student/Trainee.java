package com.danceschool.danceschool.student;

public class Trainee {
    private final String firstName;
    private final String lastName;

    public Trainee(TraineeBuilder builder) {
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
        return "Trainee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class TraineeBuilder{
        private final String firstName;
        private final String lastName;

        public TraineeBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Trainee build(){
            Trainee trainee = new Trainee(this);
            return trainee;
        }
    }
}
