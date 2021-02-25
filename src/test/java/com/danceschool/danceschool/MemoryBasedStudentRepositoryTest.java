package com.danceschool.danceschool;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.student.MemoryBasedStudentRepository;
import com.danceschool.danceschool.student.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class MemoryBasedStudentRepositoryTest {
    @Test
    @DisplayName("should create student when get correct data")
    public void shouldAddStudentToStudentList() {
        //given correct PersonalData object and correct enum Level
        PersonalData personalData = new PersonalData("Name","Surname", "Address");
        Level level = Level.valueOf("AMATEUR");

        //when add a new student with correct fields
        StudentRepository repositoryInstance = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance();
        repositoryInstance.createStudent(personalData,level);
        int actualSize = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().getStudentList().size();


        //then studentList should be one size bigger than before
        assertEquals(1,actualSize);
    }

    @Test
    @DisplayName("should remove student from studentList")
    public void shouldRemoveStudentFromStudentList() {
        //given
        PersonalData personalData = new PersonalData(null,"Surname", "Address");
        Level level = Level.valueOf("AMATEUR");
        StudentRepository repositoryInstance = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance();
        repositoryInstance.createStudent(personalData,level);
        String surname = "Surname";
        //when
        repositoryInstance.deleteStudent(surname);
        //than
        int expectedSize = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance().getStudentList().size();
        assertEquals(0,expectedSize);
    }
    
}

