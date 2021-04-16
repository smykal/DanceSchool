package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.PersonalData;

public class Secretary {
    private PersonalData personalData;


    public static final class SecretaryBuilder {
        private PersonalData personalData;

        public SecretaryBuilder() {
        }

        public static SecretaryBuilder aSecretary() {
            return new SecretaryBuilder();
        }

        public SecretaryBuilder withPersonalData(PersonalData personalData) {
            this.personalData = personalData;
            return this;
        }

        public Secretary build() {
            Secretary secretary = new Secretary();
            secretary.personalData = this.personalData;
            return secretary;
        }
    }
}
