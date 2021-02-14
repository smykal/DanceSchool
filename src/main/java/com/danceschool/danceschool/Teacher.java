package com.danceschool.danceschool;;

public class Teacher {

    private PersonalData personalData;
    private Level level;

    public static class Builder {

        private PersonalData personalData;
        private Level level;

        public Builder() {
        }

        Builder(PersonalData personalData, Level level) {
            this.personalData = personalData;
            this.level = level;
        }

        public Builder personalData(PersonalData personalData){
            this.personalData = personalData;
            return Builder.this;
        }

        public Builder level(Level level){
            this.level = level;
            return Builder.this;
        }

        public Teacher build() {
            if(this.personalData == null){
                throw new NullPointerException("The property \"personalData\" is null. "
                        + "Please set the value by \"personalData()\". "
                        + "The property \"personalData\" is required.");
            }

            return new Teacher(this);
        }
    }

    private Teacher(Builder builder) {
        this.personalData = builder.personalData;
        this.level = builder.level;
    }

    public void doSomething() {
        // do something
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "personalData=" + personalData +
                ", level=" + level +
                '}';
    }
}
