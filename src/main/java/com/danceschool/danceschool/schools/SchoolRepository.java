package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

public interface SchoolRepository {
    String createSchool(String schoolName, Address schoolAddress);
    String readSchool(String schoolName);
    String updateSchool(String schoolName, Address newSchoolAddress);
    String deleteSchool(String schoolName);
}
