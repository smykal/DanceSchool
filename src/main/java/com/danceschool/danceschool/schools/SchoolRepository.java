package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

import java.util.UUID;

public interface SchoolRepository {
    UUID createSchool(String schoolName, Address schoolAddress);
    School readSchool(UUID schoolId);
    School updateSchool(UUID schoolId, Address newSchoolAddress);
    boolean deleteSchool(UUID schoolId);
}
