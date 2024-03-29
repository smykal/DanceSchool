package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;
import com.danceschool.danceschool.members.teacher.Teacher;

import java.util.*;

public class Group {
    private String groupName;
    private UUID groupId;
    private Teacher groupFemaleTeacher;
    private Teacher groupMaleTeacher;
    private List<Student> groupStudentList = new ArrayList<Student>(10);

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public String getGroupName() { return groupName; }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Teacher getGroupFemaleTeacher() {
        return groupFemaleTeacher;
    }

    public void setGroupFemaleTeacher(Teacher groupFemaleTeacher) {
        this.groupFemaleTeacher = groupFemaleTeacher;
    }

    public Teacher getGroupMaleTeacher() {
        return groupMaleTeacher;
    }

    public void setGroupMaleTeacher(Teacher groupMaleTeacher) {
        this.groupMaleTeacher = groupMaleTeacher;
    }

    public List<Student> getGroupStudentList() {
        return groupStudentList;
    }

    public void setGroupStudentList(List<Student> groupStudentList) {
        this.groupStudentList = groupStudentList;
    }

    public Student getGroupStudentFromList(int studentNumber) {
        return groupStudentList.get(studentNumber);
    }

    public void setGroupStudentOnList(List<Student> groupStudentList, Student student){
        this.groupStudentList.add(student);
    }

    public static class Builder {

        private String groupName;
        private UUID groupId;
        private Teacher groupFemaleTeacher;
        private Teacher groupMaleTeacher;
        private List<Student> groupStudentList = new ArrayList<Student>();

        public Builder() {
        }

        Builder(String groupName, UUID groupId, Teacher groupFemaleTeacher, Teacher groupMaleTeacher, List<Student> groupStudentList) {
            this.groupName = groupName;
            this.groupId = groupId;
            this.groupFemaleTeacher = groupFemaleTeacher;
            this.groupMaleTeacher = groupMaleTeacher;
            this.groupStudentList = groupStudentList;
        }

        public Builder groupName(String groupName){
            this.groupName = groupName;
            return Builder.this;
        }

        public Builder groupId(UUID groupId){
            this.groupId = groupId;
            return Builder.this;
        }

        public Builder groupFemaleTeacher(Teacher groupFemaleTeacher){
            this.groupFemaleTeacher = groupFemaleTeacher;
            return Builder.this;
        }

        public Builder groupMaleTeacher(Teacher groupMaleTeacher){
            this.groupMaleTeacher = groupMaleTeacher;
            return Builder.this;
        }

        public Builder groupStudentList(List<Student> groupStudentList){
            this.groupStudentList = groupStudentList;
            return Builder.this;
        }

        public Builder addGroupStudentList(Student groupStudentList){
            this.groupStudentList.add(groupStudentList);
            return Builder.this;
        }

        public Group build() {
            if(this.groupName == null){
                throw new NullPointerException("The property \"groupName\" is null. "
                        + "Please set the value by \"groupName()\". "
                        + "The property \"groupName\" is required.");
            }

            return new Group(this);
        }
    }

    private Group(Builder builder) {
        this.groupName = builder.groupName;
        this.groupId = builder.groupId;
        this.groupFemaleTeacher = builder.groupFemaleTeacher;
        this.groupMaleTeacher = builder.groupMaleTeacher;
        this.groupStudentList = builder.groupStudentList;
    }
}


