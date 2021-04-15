package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

import java.util.HashMap;
import java.util.Map;

public class School {
    Map<String, Address> schools = new HashMap<>();

    private static School schoolList = new School();

    private School() {}

    public static School getSchoolListInstance() { return schoolList; }


    public String iterateSchools(Map<String, Address> map) {
        StringBuffer display = new StringBuffer();
        display.append("School name / " + "school address\n");
        for (Map.Entry<String, Address> entry : map.entrySet()) {
            display.append(entry.getKey() + "/" + entry.getValue());
        }
        return display.toString();
    }

    @Override
    public String toString() {
        return "School{" +
                "schools=" + schools +
                '}';
    }

    public Map<String, Address> getSchoolsList() {
        return schools;
    }
}
