package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.Members;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

public class Teacher extends Members {

    private PersonalData personalData;
    private Level level;

    public String getSurname() { return personalData.getSurname(); }

    public static class Builder {

        private PersonalData personalData;
        private Level level;



        public Builder() {
        }

        public Builder(PersonalData personalData) {
            this.personalData = personalData;
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
            return new Teacher(this);
        }
    }

    private Teacher(Builder builder) {
        this.personalData = builder.personalData;
        this.level = builder.level;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "personalData=" + personalData +
                ", level=" + level +
                '}';
    }
}
