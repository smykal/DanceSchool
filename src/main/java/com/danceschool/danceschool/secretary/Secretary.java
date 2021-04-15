package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.groups.Groups;
import com.danceschool.danceschool.groups.GroupInterface;
import com.danceschool.danceschool.data.PersonalData;

public class Secretary implements GroupInterface {
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
    @Override
    public Groups setUpNewGroup(String name) {
        return null;
    }

}
