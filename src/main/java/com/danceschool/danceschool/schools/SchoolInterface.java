package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

public interface SchoolInterface {
    public String addSchool(String schoolName, Address schoolAddress);
    public String removeSchool(String schoolName);
}
