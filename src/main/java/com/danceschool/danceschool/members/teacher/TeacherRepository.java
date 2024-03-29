package com.danceschool.danceschool.members.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.members.Members;

public interface TeacherRepository {
    Members createTeacher(PersonalData personalData, Level level);
    void readTeacher(String surname);
    void updateTeacher(String surname, PersonalData newPersonalData, Level newLevel);
    void deleteTeacher(String surname);
}
