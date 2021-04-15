package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.groups.Groups;
import com.danceschool.danceschool.groups.GroupInterface;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.schools.School;
import com.danceschool.danceschool.schools.SchoolInterface;

import java.util.Map;

public class Secretary implements GroupInterface, SchoolInterface  {
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

    @Override
    public String addSchool(String schoolName, Address schoolAddress) {
        Map<String,Address> schools = School.getSchoolListInstance().getSchoolsList();
        School.getSchoolListInstance().getSchoolsList().put(schoolName, schoolAddress);
        String result = School.getSchoolListInstance().iterateSchools(schools);
        return result;
    }

    @Override
    public String removeSchool(String schoolName) {
        Map<String,Address> schools = School.getSchoolListInstance().getSchoolsList();
        School.getSchoolListInstance().getSchoolsList().remove(schoolName);
        String result = School.getSchoolListInstance().iterateSchools(schools);
        return result;
    }
}
