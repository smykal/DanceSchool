package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;

import java.util.HashMap;
import java.util.Map;

public class MemoryBasedSchoolRepository implements SchoolRepository{
    Map<String, Address> schools = new HashMap<>();


    private static MemoryBasedSchoolRepository schoolsList = new MemoryBasedSchoolRepository();
    private MemoryBasedSchoolRepository() {}
    public static MemoryBasedSchoolRepository getMemoryBasedSchoolRepositoryInstance() { return schoolsList; }


    @Override
    public String createSchool(String schoolName, Address schoolAddress) {
        Map<String, Address> schools = MemoryBasedSchoolRepository.schoolsList.schools;
        schools.put(schoolName, schoolAddress);

        String result = MemoryBasedSchoolRepository
                .getMemoryBasedSchoolRepositoryInstance()
                .iterateSchools(schools);
        System.out.println(result);
        return result;
    }

    @Override
    public String readSchool(String schoolName) {
        Address address = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.get(schoolName);
        StringBuffer display = new StringBuffer();
        display.append("Informacje o szkole " + schoolName + ": " + address.toString());
        System.out.println(display);
        return display.toString();
    }

    @Override
    public String updateSchool(String schoolName, Address newSchoolAddress) {
        Boolean isSchoolExisting = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .isSchoolExisting(schoolName);
        if (isSchoolExisting) {
            StringBuffer display = new StringBuffer();
            String oldAddress = MemoryBasedSchoolRepository
                    .getMemoryBasedSchoolRepositoryInstance()
                    .schools
                    .get(schoolName)
                    .toString();
            display.append("Stary adres szkoły ")
                    .append(schoolName)
                    .append(" to: ")
                    .append(oldAddress)
                    .append("\n");
            MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                    .schools.put(schoolName,newSchoolAddress);
            String newAddress = MemoryBasedSchoolRepository
                    .getMemoryBasedSchoolRepositoryInstance()
                    .schools
                    .get(schoolName)
                    .toString();
            display.append("Nowy adres szkoły " + schoolName + " to: " + newAddress + "\n");

            System.out.println(display);
            return display.toString();
        } else
            return "No such school";
    }

    @Override
    public String deleteSchool(String schoolName) {
        Map<String, Address> schools = MemoryBasedSchoolRepository
                .getMemoryBasedSchoolRepositoryInstance().schools;
        schools.remove(schoolName);
        String result = MemoryBasedSchoolRepository
                .getMemoryBasedSchoolRepositoryInstance().iterateSchools(schools);
        System.out.println(result);
        return result;
    }

    public String iterateSchools(Map<String, Address> map) {
        StringBuffer display = new StringBuffer();
        display.append("School name / " + "school address\n");
        for (Map.Entry<String, Address> entry : map.entrySet()) {
            display.append(entry.getKey() + "/" + entry.getValue() + "\n");
        }
        return display.toString();
    }

    public Boolean isSchoolExisting(String schoolName) {
        boolean result = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.containsKey(schoolName);
        return result;
    }
}
