package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.PersonalData;

public class Secretary {

    private PersonalData personalData;

    public static class Builder {

        private PersonalData personalData;

        public Builder() {
        }

        public Builder(PersonalData personalData) {
            this.personalData = personalData;
        }

        public Builder personalData(PersonalData personalData){
            this.personalData = personalData;
            return Builder.this;
        }

        public Secretary build() {
            return new Secretary(this);
        }
    }

    private Secretary(Builder builder) {
        this.personalData = builder.personalData;
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }
}
