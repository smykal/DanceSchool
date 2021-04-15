package com.danceschool.danceschool.members.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;

import java.io.IOException;
import java.util.UUID;

public interface StudentRepository {

    UUID createStudent(PersonalData personalData, Level level) throws IOException;
    Student readStudent(UUID uuid) throws IOException;
    Student updateStudent(UUID uuid, PersonalData newPersonalData, Level newLevel)
            throws IOException;
    UUID deleteStudent(UUID uuid) throws IOException;
}
