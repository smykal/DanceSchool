package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedSchoolRepositoryTest {

    public static final String SCHOOL_NAME = "szkola01";
    public static final String CITY = "Kraków";
    public static final String POSTAL_CODE = "30-818";
    public static final String STREET_NAME = "Długa";
    public static final String BLOCK_NUMBER = "3";
    public static final String APARTMENT_NUMBER = "5";
    public static final MemoryBasedSchoolRepository MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
            = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance();

    @BeforeEach
    public void setUp() {
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, APARTMENT_NUMBER);
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(SCHOOL_NAME,address);
    }

    private Address createAddress(String city, String postalCode, String streetName, String blockNumber,
                                  String apartmentNumber) {
        return new Address.Builder()
                .city(city)
                .postalCode(postalCode)
                .street(streetName)
                .blockNumber(blockNumber)
                .apartmentNumber(apartmentNumber)
                .build();
    }

    @AfterEach
    public void tearDown() {
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchools().clear();
    }

    @Test
    void shouldCheckIfSchoolExist() {
        //given

        //when

        //than
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(SCHOOL_NAME);
        assertTrue(actual);
    }

    @Test
    void shouldCheckIfNewSchoolExist(){
        //given
        String schoolName = "sabrosa";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //than
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(schoolName);

        assertTrue(actual);
    }

    @Test
    void shouldCheckIfNewSchoolAddressExist(){
        //given
        String schoolName = "sabrosa";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //than
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools().get(schoolName).getClass().getSimpleName();

        assertEquals("Address",actual);
    }


    @Test
    void shouldCreateSchoolAndIncreaseQuantityOfSchools(){
        //given
        String schoolName = "lofts";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        int expectedSize = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools()
                .size();

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //than
        int actualSize = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools()
                .size();
        assertEquals(expectedSize + 1, actualSize);
    }

    @Test
    void shouldReadSchoolAndGiveStringAsAResult() {
        //given
        String schoolForCheck = SCHOOL_NAME;

        //when
        String actualClass = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .readSchool(schoolForCheck).getClass().getSimpleName();

        //than
        assertEquals("String",actualClass);
    }

    @Test
    void shouldGiveBackSchoolNameInsideResult() {
        //given
        String schoolName = SCHOOL_NAME;

        //when
        StringBuilder result = new StringBuilder();
        result.append(MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
        .readSchool(schoolName));

        //than
        boolean isTrue = result.toString().contains(schoolName);
        assertTrue(isTrue);
    }

    @Test
    void shouldReplaceSchoolAddress() {
        //given
        String schoolForUpdate = SCHOOL_NAME;
        Address newAddress = createAddress(
                "Wrocław", "22-111", "Krótka", "A", "B");

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(schoolForUpdate,newAddress);

        //than
        Address actualAddress = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools()
                .get(schoolForUpdate);
        assertEquals(actualAddress.getCity(),"Wrocław");
        assertEquals(actualAddress.getPostalCode(),"22-111");
        assertEquals(actualAddress.getStreet(),"Krótka");
        assertEquals(actualAddress.getBlockNumber(),"A");
        assertEquals(actualAddress.getApartmentNumber(),"B");
    }

    @Test
    void shouldInformThatSchoolDoesNotExistWhenWrongSchoolNameIsGiven() {
        //given
        String wrongSchoolName = "non proper school name";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(wrongSchoolName, address);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void shouldInformThatSchoolDoesNotExistWhenNullIsGivenAsSchoolName() {
        //given
        String wrongSchoolName = null;
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(wrongSchoolName, address);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteSchoolFromList() {
        //given
        String schoolNameToDelete = SCHOOL_NAME;

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .deleteSchool(schoolNameToDelete);

        //than
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools()
                .containsKey(schoolNameToDelete);
        assertFalse(actual);
    }

    @Test
    void shouldGiveInformationThatSchoolDoesNotExist() {
        //given
        String wrongSchoolName = "high school";

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .deleteSchool(wrongSchoolName);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void shouldGiveInformationThatSchoolDoesNotExistWhenWeAskForNullSchoolName() {
        //given

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .deleteSchool(null);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDisplayAllSchools() {
        //given
        Map schoolsListToIterate = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchools();

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .iterateSchools(schoolsListToIterate);

        //than
        String expected = "School name / school address\n" +
                "szkola01/Address{city='Kraków', postalCode='30-818'," +
                " street='Długa', blockNumber='3', apartmentNumber='5'}" + "\n";
        assertEquals(expected, actual);
    }

    @Test
    void shouldConfirmThatSchoolsWereGivenBackAsResult() {
        //given
        Map<String, Address> schoolsListToIterate = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools();
        String schoolName = "salsa";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //when
        String result = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .iterateSchools(schoolsListToIterate);

        //than
        boolean containsSchoolName01 = result.contains(schoolName);
        boolean containsSchoolName02 = result.contains(SCHOOL_NAME);
        assertTrue(containsSchoolName01);
        assertTrue(containsSchoolName02);
    }

    @Test
    void shouldInformThatSchoolListIsEmpty() {
        //given
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools()
                .clear();
        Map<String ,Address> schoolList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchools();

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .iterateSchools(schoolList);

        //than
        String expected = "list is empty";
        assertEquals(expected, actual);
    }


    @Test
    void shouldConfirmThatSchoolIsExisting () {
        //given
        String schoolName = "sabrosa dance school";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //when
        Boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(schoolName);

        //than
        assertTrue(actual);
    }

    @Test
    void shouldConfirmThatSchoolIsNotExisting () {
        //given
        String schoolWrongName = "uLuisa";

        //when
        Boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(schoolWrongName);

        //than
        assertFalse(actual);
    }
}