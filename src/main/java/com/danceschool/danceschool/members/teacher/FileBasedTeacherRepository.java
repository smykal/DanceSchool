package com.danceschool.danceschool.members.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.members.Members;

public class FileBasedTeacherRepository implements TeacherRepository    {
    @Override
    public Members createTeacher(PersonalData personalData, Level level) {
        return null;
    }

    @Override
    public void readTeacher(String surname) {

    }

    @Override
    public void updateTeacher(String surname, PersonalData newPersonalData, Level newLevel) {

    }

    @Override
    public void deleteTeacher(String surname) {

    }
}
