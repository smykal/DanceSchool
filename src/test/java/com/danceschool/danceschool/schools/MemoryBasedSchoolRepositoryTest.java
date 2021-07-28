package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        UUID SCHOOL_UUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(SCHOOL_NAME, address);

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
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList().clear();
    }

    @Test
    void shouldCheckIfSchoolExist() {
        //given
        List<School> schoolList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList();
        //when

        //then
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(UUID.randomUUID(),schoolList );
        assertTrue(actual);
    }

    @Test
    void shouldCheckIfNewSchoolExist(){
        //given
        String schoolName = "sabrosa";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        //when
        UUID schoolUUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName, address);
        List<School> schoolList = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList();

        //then
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(schoolUUID, schoolList);

        assertTrue(actual);
    }

    @Test
    void shouldCheckIfNewSchoolAddressExist(){
        //given
        String schoolName = "sabrosa";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        UUID schoolUUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName, address);

        //then
        Address actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .readSchool(schoolUUID).getSchoolAddress();

        assertEquals(address,actual);
    }


    @Test
    void shouldCreateSchoolAndIncreaseQuantityOfSchools(){
        //given
        String schoolName = "lofts";
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);
        int expectedSize = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList()
                .size();

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .createSchool(schoolName,address);

        //then
        int actualSize = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList()
                .size();
        assertEquals(expectedSize + 1, actualSize);
    }

    @Test
    void shouldReadSchoolAndGiveStringAsAResult() {
        //given
        UUID randomUUID = UUID.randomUUID();

        //when
        String actualClass = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .readSchool(randomUUID).getClass().getSimpleName();

        //then
        assertEquals("String",actualClass);
    }

    @Test
    void shouldGiveBackSchoolNameInsideResult() {
        //given
        String schoolName = SCHOOL_NAME;
        UUID schoolUUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList().get(0).getSchoolId();

        //when
        StringBuilder result = new StringBuilder();
        result.append(MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
        .readSchool(schoolUUID));

        //then
        boolean isTrue = result.toString().contains(schoolName);
        assertTrue(isTrue);
    }

    @Test
    void shouldReplaceSchoolAddress() {
        //given
        Address newAddress = createAddress(
                "Wrocław", "22-111", "Krótka", "A", "B");
        UUID random_school_UUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.createSchool("random school", newAddress);

        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(random_school_UUID,newAddress);

        //then
        Address actualAddress = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .readSchool(random_school_UUID).getSchoolAddress();
        assertEquals(actualAddress.getCity(),"Wrocław");
        assertEquals(actualAddress.getPostalCode(),"22-111");
        assertEquals(actualAddress.getStreet(),"Krótka");
        assertEquals(actualAddress.getBlockNumber(),"A");
        assertEquals(actualAddress.getApartmentNumber(),"B");
    }

    @Test
    void shouldInformThatSchoolDoesNotExistWhenWrongSchoolNameIsGiven() {
        //given
        UUID wrongSchoolUUID = UUID.randomUUID();
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        School actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(wrongSchoolUUID, address);

        //then
        String expected = "result of that asseration 'll be interesting";
        assertEquals(expected, actual);
    }

    @Test
    void shouldInformThatSchoolDoesNotExistWhenNullIsGivenAsSchoolUUID() {
        //given
        UUID wrongSchoolUUID = null;
        Address address = createAddress(CITY, POSTAL_CODE, STREET_NAME, BLOCK_NUMBER, BLOCK_NUMBER);

        //when
        School actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .updateSchool(wrongSchoolUUID, address);

        //then
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteSchoolFromList() {
        //given
        UUID schoolIdUUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList()
                .get(0)
                .getSchoolId();
        //when
        MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .deleteSchool(schoolIdUUID);

        //then
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList()
                .contains(schoolIdUUID);
        assertFalse(actual);
    }

    @Test
    void shouldGiveInformationThatSchoolDoesNotExist() {
        //given
        UUID schoolIdUUID = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .getSchoolList()
                .get(0)
                .getSchoolId();

        //when
        boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .deleteSchool(schoolIdUUID);

        //then
        String expected = "No such school";
        assertEquals(expected, actual);
    }


    @Test
    void shouldDisplayAllSchools() {
        //given
        List<School> schoolsListToIterate = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE.getSchoolList();

        //when
        String actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .iterateSchools(schoolsListToIterate);

        //then
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

        //then
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

        //then
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

        //then
        assertTrue(actual);
    }

    @Test
    void shouldConfirmThatSchoolIsNotExisting () {
        //given
        String schoolWrongName = "uLuisa";

        //when
        Boolean actual = MEMORY_BASED_SCHOOL_REPOSITORY_INSTANCE
                .isSchoolExisting(schoolWrongName);

        //then
        assertFalse(actual);
    }
}