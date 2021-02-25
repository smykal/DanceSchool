package com.danceschool.danceschool.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedTeacherRepositoryTest {

    @Test

    void createTeacher() {
        //given
        PersonalData personalData = new PersonalData("name", "surname", "address");
        Level level = Level.AMATEUR;
        //when
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().createTeacher(personalData,level);
        int actualTeacherListSize = MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().getTeacherList().size();
        //than
        assertEquals(1,actualTeacherListSize);

    }

    @Test
    void readTeacher() {
        //given
        PersonalData personalData = new PersonalData("name", "surname", "address");
        Level level = Level.AMATEUR;
        String surname = "surname";
        //when
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().createTeacher(
                personalData,
                level);
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().readTeacher(surname);
        //than
        //??
    }

    @Test
    void updateTeacher() {
        //given
        PersonalData personalData = new PersonalData("name", "surname",  "address");
        PersonalData newPersonalData = new PersonalData("newName", "newSurname", "newAddress");
        Level level = Level.AMATEUR;
        Level newLevel = Level.PROFFESIONAL;
        String surname = "surname";
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().createTeacher(
                personalData,
                level);
        String actualName = MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().getTeacherList().get(0).getSurname();
        //when
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().updateTeacher(
                surname,
                newPersonalData,
                newLevel );
        //than
        //fuck
    }

    @Test
    void deleteTeacher() {
        //given
        PersonalData personalData = new PersonalData("name", "surname",  "address");
        PersonalData newPersonalData = new PersonalData("newName", "newSurname", "newAddress");
        Level level = Level.AMATEUR;
        Level newLevel = Level.PROFFESIONAL;
        String surname = "surname";
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().createTeacher(
                personalData,
                level);
        //when
        MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().deleteTeacher(surname);
        int actualSize = MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance().getTeacherList().size();

        //than
        assertEquals(0,actualSize);

    }
}