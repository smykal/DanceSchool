package com.danceschool.danceschool.secretary;

import com.danceschool.danceschool.data.Group;
import com.danceschool.danceschool.data.GroupInterface;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.teacher.Teacher;

import java.util.List;

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
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }

    @Override
    public Group setUpNewGroup(String name) {
        Group group = new Group();
        group.setName(name);
        return group;
    }

    @Override
    public String addFemaleTeacher(Group group, Teacher teacher) {
        List<Teacher> teacherList = group.getTeacherList();
        try {
            teacherList.add(teacher);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
            System.out.println("There is no more space on teacherList" +
                    " delete or update some of teachers");
        }

        return group.iteratorTeacherList();
    }

    @Override
    public String addMaleTeacher() {
        return null;
    }

    @Override
    public String addFemaleStudent() {
        return null;
    }

    @Override
    public String addMaleStudent() {
        return null;
    }
}
