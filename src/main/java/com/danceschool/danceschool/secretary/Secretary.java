package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.PersonalData;
import com.danceschool.danceschool.Position;
import com.danceschool.danceschool.student.Student;
import com.danceschool.danceschool.teacher.Teacher;
import org.springframework.cache.interceptor.NamedCacheResolver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Secretary {
    private final Position position;
    private final String firstName;
    private final String lastName;

    private Secretary(SecretaryBuilder builder) {
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
        return "Secretary: {" +
                "position = " + position +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }

    public static class SecretaryBuilder {
        private final Position position;
        private final String firstName;
        private final String lastName;

        public SecretaryBuilder(Position position, String firstName, String lastName) {
            this.position = position;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Secretary build() {
            Secretary secretary = new Secretary(this);
            return secretary;
        }
    }
}
