package com.danceschool.danceschool.members.student;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MemoryBasedStudentRepositoryTest {

    public static final MemoryBasedStudentRepository MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE =
            MemoryBasedStudentRepository.getMemoryBasedStudentRepositoryInstance();
    @Mock
    PersonalData mockPersonalData;
    @Mock
    Student mockStudent01;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockStudent01.getId()).thenReturn(UUID.fromString("0-0-0-0-0"));
    }

    @AfterEach
    void tearDown() {
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.deleteAllStudents();
    }

    @Test
    @DisplayName("should add new student and increase student size list")
    void shouldIncreaseListSize() throws IOException {
        //given

        //when
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData, Level.AMATEUR);

        //than
        int actualSize = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .getStudentList()
                .size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void shouldAddNewStudent() throws IOException {
        //given

        //when
        UUID expectedUUID = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData, Level.AMATEUR);

        //than
        UUID actualUUID = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .getStudentList()
                .get(0)
                .getId();
        assertEquals(expectedUUID, actualUUID);
    }

    @Test
    void shouldAddNewStudentToStudentList() throws IOException {
        //given
        String testCity = "TestCity";
        String testPostalCode = "23-333";
        String testStreet = "TestStreet";
        String testBlockNumber = "23";
        String testApartmentNumber = "34";
        String testName = "TestName";
        String testSurname = "TestSurname";

        Address address = new Address.Builder()
                .city(testCity)
                .postalCode(testPostalCode)
                .street(testStreet)
                .blockNumber(testBlockNumber)
                .apartmentNumber(testApartmentNumber)
                .build();

        PersonalData personalData = new PersonalData.PersonalDataBuilder()
                .withName(testName)
                .withSurname(testSurname)
                .withAddress(address)
                .build();
        //when
        UUID actualUUID = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(personalData,Level.AMATEUR);

        //than
        Student student = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .readStudent(actualUUID);
        UUID expectedUUID = student.getId();
        String expectedCity = testCity;
        String expectedPostalCode = testPostalCode;
        String expectedStreet = testStreet;
        String expectedBlock = testBlockNumber;
        String expectedApartment = testApartmentNumber;
        String expectedName = testName;
        String expectedSurname = testSurname;

        String actualCity = student.getCity();
        String actualPostalCode = student.getPostalCode();
        String actualStreet = student.getStreet();
        String actualBlock = student.getBlockNumber();
        String actualApartment = student.getApartmentNumber();
        String actualName = student.getName();
        String actualSurname = student.getSurname();

        assertEquals(expectedCity,actualCity);
        assertEquals(expectedPostalCode,actualPostalCode);
        assertEquals(expectedStreet,actualStreet);
        assertEquals(expectedBlock,actualBlock);
        assertEquals(expectedApartment,actualApartment);
        assertEquals(expectedName,actualName);
        assertEquals(expectedSurname,actualSurname);
        assertEquals(expectedUUID,actualUUID);
    }

    @Test
    void shouldGiveStudentClasAsResult() throws IOException {
        //given
        UUID uuid = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData, Level.AMATEUR);

        //when
        Student student = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .readStudent(uuid);
        //than
        String actual = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .readStudent(uuid)
                .getClass()
                .getSimpleName();
        String expected = "Student";
        assertEquals(expected, actual);
    }

    @Test
    void shouldGiveBackStudentAsResult() throws IOException {
        //given
        UUID studentUuid = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);

        //when
        Student student = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .readStudent(studentUuid);

        //than
        assertEquals(student.getClass(), Student.class);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                ()->{
                    MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                            .readStudent(UUID.fromString("fee"));
                });
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenStudentNotFound() throws IOException {
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.createStudent(mockPersonalData, Level.AMATEUR);
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.createStudent(mockPersonalData, Level.AMATEUR);
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.createStudent(mockPersonalData, Level.AMATEUR);
        assertThrows(UserNotFoundException.class,
                ()->{
                    MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                            .readStudent(UUID.randomUUID());
                });
    }



    @Test
    void shouldChangeLevelForProfessional() throws IOException {
        //given
        UUID studentUuid = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        //when
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .updateStudent(studentUuid,mockPersonalData,Level.PROFESSIONAL);

        //than
        Level expected = Level.PROFESSIONAL;
        Level actual = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.getStudentList().get(0).getLevel();
        assertEquals(expected,actual);
    }

    @Test
    void shouldDeleteStudent() throws IOException {
        //given
        UUID studentUuid01 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        UUID studentUuid02 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        UUID studentUuid03 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        List<Student> list = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .getStudentList();

        //when
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .deleteStudent(studentUuid01);
        boolean expectedFalse = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.searchUUID(studentUuid01, list);

        //than
        assertFalse(expectedFalse);

    }
    @Test
    void shouldLeftListSize2() throws IOException {
        //given
        UUID studentUuid01 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        UUID studentUuid02 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        UUID studentUuid03 = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        List<Student> list = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .getStudentList();

        //when
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .deleteStudent(studentUuid01);
        int expected = 2;
        int actual = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.getStudentList().size();

        //than
        assertEquals(expected, actual);

    }

    @Test
    void shouldReturnList() throws IOException {
        //given
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);

        //when
        List<Student> list = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE.getStudentList();

        //than
        assertEquals(list.getClass(), ArrayList.class);
    }

    @Test
    void shouldLeftEmptyList() throws IOException {
        //given
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .createStudent(mockPersonalData,Level.AMATEUR);

        //when
        MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .deleteAllStudents();

        //than
        int expected = 0;
        int actual = MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE
                .getStudentList()
                .size();
        assertEquals(expected, actual);
    }
}