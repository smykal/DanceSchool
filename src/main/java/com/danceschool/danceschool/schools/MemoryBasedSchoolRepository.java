package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.exceptions.SchoolNotFoundException;

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
    public UUID createSchool(String schoolName, Address schoolAddress) {
        School school = new School.Builder()
                .schoolName(schoolName)
                .schoolAddress(schoolAddress)
                .build();
        getMemoryBasedSchoolRepositoryInstance().getSchoolList().add(school);
        int indexOfSchool = getMemoryBasedSchoolRepositoryInstance().getSchoolList().indexOf(school);
        UUID schoolId = getMemoryBasedSchoolRepositoryInstance().getSchoolList().get(indexOfSchool).getSchoolId();
        return schoolId;
    }

    @Override
    public School readSchool(UUID schoolId) {
        List<School> schoolsList = getMemoryBasedSchoolRepositoryInstance()
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
        List<School> schoolsList = getMemoryBasedSchoolRepositoryInstance()
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
        List<School> schoolsList = getMemoryBasedSchoolRepositoryInstance()
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

    public String iterateSchools(List<School> schoolList) {
        if (!schoolList.isEmpty()) {
            StringBuffer display = new StringBuffer();
            display.append("School name / " + "school address\n");
            for (int i = 0; i < schoolList.size(); i++) {
                display.append(schoolList.get(i).getSchoolAddress() + " "
                                + schoolList.get(i).getSchoolName() + " "
                                + schoolList.get(i).getSchoolId());
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
