package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.exceptions.SchoolNotFoundException;
import com.danceschool.danceschool.groups.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemoryBasedSchoolRepository implements SchoolRepository{
    public static final MemoryBasedSchoolRepository MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE =
            MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance();
    private List<School> schoolList = new ArrayList<School>();


    private static MemoryBasedSchoolRepository schoolsList = new MemoryBasedSchoolRepository();
    private MemoryBasedSchoolRepository() {}
    public static MemoryBasedSchoolRepository getMemoryBasedSchoolRepositoryInstance() { return schoolsList; }


    @Override
    public School createSchool(String schoolName, Address schoolAddress) {
        School school = new School.Builder()
                .schoolName(schoolName)
                .schoolAddress(schoolAddress)
                .build();
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList().add(school);
        return school;
    }

    @Override
    public School readSchool(UUID schoolId) {
        List<School> schoolsList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList();
        if (!isSchoolExisting(schoolId, schoolsList)) {
            System.out.println("school with id: " + schoolId.toString() + " not found");
            throw new SchoolNotFoundException("school with id: " + schoolId.toString() + " not found");
        } else {
            return findSchool(schoolId, schoolsList);
        }
    }

    @Override
    public School updateSchool(UUID schoolId, Address newSchoolAddress) {
        List<School> schoolsList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList();
        if (!isSchoolExisting(schoolId, schoolsList)) {
            System.out.println("school with id: " + schoolId.toString() + " not found");
            throw new SchoolNotFoundException("school with id: " + schoolId.toString() + " not found");
        } else {
            String schoolName = findSchool(schoolId, schoolsList).getSchoolName();
            School newSchool = new School.Builder()
                    .schoolName(schoolName)
                    .schoolAddress(newSchoolAddress)
                    .build();
            School oldSchool = findSchool(schoolId, schoolsList);
            schoolsList.remove(oldSchool);
            schoolsList.add(newSchool);
            return newSchool;
        }
    }

    @Override
    public boolean deleteSchool(UUID schoolId) {
        List<School> schoolsList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList();
        if (!isSchoolExisting(schoolId, schoolsList)) {
            System.out.println("school with id: " + schoolId.toString() + " not found");
            throw new SchoolNotFoundException("school with id: " + schoolId.toString() + " not found");
        } else {
            School school = findSchool(schoolId, schoolsList);
            schoolsList.remove(school);
            return true;
        }
    }

    public String iterateSchools(Map<String, Address> map) {
        if (!map.isEmpty()) {
            StringBuffer display = new StringBuffer();
            display.append("School name / " + "school address\n");
            for (Map.Entry<String, Address> entry : map.entrySet()) {
                display.append(entry.getKey() + "/" + entry.getValue() + "\n");
            }
            return display.toString();
        }
        else
            return "list is empty";
    }

    public boolean isSchoolExisting(UUID uuid, List<School> schoolsList) {
        boolean result = false;
        for (int i = 0; i < schoolsList.size(); i++) {
            if (schoolsList.get(i).getSchoolId().equals(uuid)) {
                result = true;
                break;
            }
        }
        return result;
    }
    private School findSchool(UUID uuid, List<School> schoolsList) {
        for (int i = 0; i < schoolsList.size(); i++) {
            if (schoolsList.get(i).getSchoolId().equals(uuid)) {
                return schoolsList.get(i);
            }
        }
        return null;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }
}
