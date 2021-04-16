package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

public interface SchoolRepository {
    public String createSchool(String schoolName, Address schoolAddress);
    public String readSchool(String schoolName);
    public String updateSchool(String schoolName, Address newSchoolAddress);
    public String deleteSchool(String schoolName);
}
