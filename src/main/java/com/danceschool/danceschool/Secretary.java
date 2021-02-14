package com.danceschool.danceschool;

import java.util.Iterator;

;

public class Secretary implements ShowGroupMembers, ChangeTeacher, AddStudent, RemoveStudent {

    private PersonalData personalData;


    public static class Builder {

        private PersonalData personalData;

        public Builder() {
        }

        Builder(PersonalData personalData) {
            this.personalData = personalData;
        }

        public Builder personalData(PersonalData personalData){
            this.personalData = personalData;
            return Builder.this;
        }

        public Secretary build() {
            if(this.personalData == null){
                throw new NullPointerException("The property \"personalData\" is null. "
                        + "Please set the value by \"personalData()\". "
                        + "The property \"personalData\" is required.");
            }

            return new Secretary(this);
        }
    }

    private Secretary(Builder builder) {
        this.personalData = builder.personalData;
    }

    public void doSomething() {
        // do something
    }

    @Override
    public String toString() {
        return "Secretary{" +
                "personalData=" + personalData +
                '}';
    }

    @Override
    public void showGroupMembers(Group group) {
        System.out.println(group.getClass().getSimpleName() +"  " + group.getClass().getCanonicalName());
        System.out.println(group.level);
        System.out.println(group.teacher);
        Iterator<Student> studentIterator = group.studentSet.iterator();
        while (studentIterator.hasNext()) {
            System.out.println(studentIterator.next());
        }
        System.out.println("\n");
    }

    @Override
    public void changeTeacher(Group group, Teacher newTeacher) {
        group.setTeacher(newTeacher);
    }

    @Override
    public void addStudent(Group group, Student student) {
        group.studentSet.add(student);
    }

    @Override
    public void removeStudent(Group group, Student student) {
        group.studentSet.remove(student);
    }
}
