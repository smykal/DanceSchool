package com.danceschool.danceschool.members.teacher;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.members.Members;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MemoryBasedTeacherRepositoryTest {

    public static final MemoryBasedTeacherRepository MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE =
            MemoryBasedTeacherRepository.getMemoryBasedTeacherRepositoryInstance();

    @Mock
    Teacher mockTeacher;
    @Mock
    PersonalData mockPersonalData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockPersonalData.getSurname()).thenReturn("testSurname");
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetBackList() {
        //given

        //when
        List<Teacher> teacherList = MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.getTeacherList();

        //than
        assertEquals(teacherList.getClass(), ArrayList.class);
    }

    @Test
    void shouldCreateTeacher() {
        //given
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.createTeacher(mockPersonalData, Level.AMATEUR);
        //when
        Members teacher = MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.getTeacherList().get(0);

        //than
        assertEquals(Teacher.class, teacher.getClass());
    }

    @Test
    void shouldReturnTeacher() {
        //given
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.createTeacher(mockPersonalData, Level.AMATEUR);
        //when
        Members teacher = MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.getTeacherList().get(0);

        //than
        assertEquals(Teacher.class, teacher.getClass());
    }

    @Test
    void updateTeacher() {
        //given
        mockPersonalData.setSurname("testSurname");
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.createTeacher(mockPersonalData, Level.AMATEUR);
        //when
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.updateTeacher(
                mockPersonalData.getSurname(), mockPersonalData, Level.PROFESSIONAL);
        //than
        String expected = "testSurname";
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.readTeacher("testSurname");


    }

    @Test
    void deleteTeacher() {
        //given
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.createTeacher(mockPersonalData, Level.AMATEUR  );

        //when
        MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE.deleteTeacher("testSurname");

        //than
        int expected = 0;
        int actual = MEMORY_BASED_TEACHER_REPOSITORY_INSTANCE
                .getTeacherList()
                .size();
        assertEquals(expected, actual);
    }
}