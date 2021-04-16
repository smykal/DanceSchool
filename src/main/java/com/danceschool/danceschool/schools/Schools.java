package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

import java.util.HashMap;
import java.util.Map;

public class Schools {
    Map<String, Address> schools = new HashMap<>();

    private static Schools schoolsList = new Schools();

    private Schools() {}

    public static Schools getSchoolListInstance() { return schoolsList; }

}
