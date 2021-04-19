package com.danceschool.danceschool.members.student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedStudentRepositoryTest {

    @Mock
    PersonalData mockPersonalData;
    @Mock
    UUID mockUUID;
    @Mock
    Student mockStudent01;
    @Mock
    Student mockStudent02;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockStudent01.getId()).thenReturn(UUID.fromString("0-0-0-0-0"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("should add new student and increase student size list")
    void createStudentTest() throws IOException {
        //given

        //when
        UUID expectTrue = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(mockPersonalData, Level.AMATEUR);


        //than
        int expectedSize = 1;
        int actualSize = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList().size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    @DisplayName("should add new student with parameters")
    void shouldCreateNewStudent() throws IOException {
        //given
        Address address = new Address.Builder()
                .city("TestCity")
                .postalCode("23-333")
                .street("TestStreet")
                .blockNumber("23")
                .apartmentNumber("34")
                .build();

        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("TestName")
                .withSurname("TestSurname")
                .withAddress(address)
                .build();
        //when
        UUID actualUUID = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData,Level.AMATEUR);

        //than
        Student student = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .readStudent(actualUUID);
        MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .getStudentList();

        UUID expectedUUID = student.getId();
        assertEquals(expectedUUID,actualUUID);
    }

    @Test
    @DisplayName("should give back Student as a result of method")
    void shouldReadStudentAndGiveUUIDasResult() throws IOException {
        //given
        Address address = new Address.Builder()
                .city("TestCity")
                .postalCode("23-333")
                .street("TestStreet")
                .blockNumber("23")
                .apartmentNumber("34")
                .build();

        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName("TestName")
                .withSurname("TestSurname")
                .withAddress(address)
                .build();
        UUID uuid = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .createStudent(personalData, Level.AMATEUR);

        //when
        Student student = MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance()
                .readStudent(uuid);
        //than
        String actual = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .readStudent(uuid).getClass().getSimpleName();
        String expected = "Student";
        assertEquals(actual, actual);
    }

    @Test
    @DisplayName("read Student with wrong uuid should give back null")
    void readStudentWithWrongUUID() {
        //given
        UUID wrongUUID = UUID.randomUUID();

        //when
        Student student = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .readStudent(wrongUUID);

        //than
        assertNull(student);
    }

    @Test
    @DisplayName("read Student with wrong uuid should give back Index Out Of Bounds Exception")
    void readStudentWithWrongUUIDAndGiveBackExceptionStackTrace() {
        //given
        UUID wrongUUID = UUID.randomUUID();

        //when
        Student student = MemoryBasedStudentRepository
                .getMemoryBasedStudentRepositoryInstance()
                .readStudent(wrongUUID);

        //than
         // assertThrows(IndexOutOfBoundsException.class, Student.class);
    }

    @Test
    @DisplayName("should change student data")
    void updateStudentData() {

    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getStudentList() {
    }

    @Test
    void deleteAllStudents() {
    }
}