package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

import java.util.UUID;

public class School {

    private String schoolName;
    private Address schoolAddress;
    private UUID schoolId;

    public static class Builder {

        private String schoolName;
        private Address schoolAddress;
        private UUID schoolId;

        public Builder() {
        }

        Builder(String schoolName, Address schoolAddress, UUID schoolId) {
            this.schoolName = schoolName;
            this.schoolAddress = schoolAddress;
            this.schoolId = schoolId;
        }

        public Builder schoolName(String schoolName){
            this.schoolName = schoolName;
            return Builder.this;
        }

        public Builder schoolAddress(Address schoolAddress){
            this.schoolAddress = schoolAddress;
            return Builder.this;
        }

        public Builder schoolId(UUID schoolId){
            this.schoolId = schoolId;
            return Builder.this;
        }

        public School build() {
            if(this.schoolName == null){
                throw new NullPointerException("The property \"schoolName\" is null. "
                        + "Please set the value by \"schoolName()\". "
                        + "The property \"schoolName\" is required.");
            }

            return new School(this);
        }
    }

    private School(Builder builder) {
        this.schoolName = builder.schoolName;
        this.schoolAddress = builder.schoolAddress;
        this.schoolId = UUID.randomUUID();
    }

    public UUID getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }
}