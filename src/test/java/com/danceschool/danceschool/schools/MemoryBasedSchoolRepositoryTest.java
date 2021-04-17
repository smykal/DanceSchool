package com.danceschool.danceschool.schools;

import com.danceschool.danceschool.data.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedSchoolRepositoryTest {

    @BeforeAll
    @DisplayName("should add one school to MAP<K,V>")
    public  static void setUp() {
        String schoolName = "szkola01";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        //when
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool(schoolName,address);
    }
    @Test
    @DisplayName("after createSchool should put into MAP<K,V> K = nazwa szkoły")
    void shouldCreateSchoolAndAddNewKeyValue() {
        //given
        String schoolName = "szkoła";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        //when
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
        .createSchool(schoolName,address);
        //than
        boolean actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.containsKey(schoolName);
        assertTrue(actual);
    }

    @Test
    @DisplayName("adter createSchool should put into MAP<K,V> V = Address - class Address")
    void shouldCreateSchoolAndAddNewAddressValue(){
        //given
        String schoolName = "szkoła";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        //when
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool(schoolName,address);

        //than
        String actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.get(schoolName).getClass().getSimpleName();

        assertEquals("Address",actual);
    }

    @Test
    @DisplayName("should create school and increase MAP-size up")
    void shouldCreateSchoolAndIncreaseMapSize(){
        //given
        String schoolName = "szkoła";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        //when
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool(schoolName,address);

        //than
        int actualSize = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.size();
        int expectedSize = 2;
        assertEquals(expectedSize,actualSize);
    }

    @Test
    @DisplayName("should give String as a result")
    void shouldReadSchoolAndGiveStringAsAResult() {
        //given
        String schoolForCheck = "szkola01";
        //when
        String actualClass = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .readSchool(schoolForCheck).getClass().getSimpleName();

        //than
        assertEquals("String",actualClass);
    }
    @Test
    @DisplayName("should contains schoolName in result")
    void shouldGiveBackSchoolNameInResult() {
        //given
        String schoolForCheck = "szkola01";
        //when
        StringBuffer display = new StringBuffer();
        display.append(MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
        .readSchool(schoolForCheck));

        //than
        boolean isTrue = display.toString().contains(schoolForCheck);
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should change school01 address")
    void shouldUpdateSchoolAddress() {
        //given
        String schoolForUpdate = "szkola01";
        Address newAddress = new Address.Builder()
                .city("Wrocław")
                .postalCode("22-111")
                .street("Krótka")
                .blockNumber("A")
                .apartmentNumber("B")
                .build();

        //when
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .updateSchool(schoolForUpdate,newAddress);

        //than
        Address actualAddress = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .schools.get(schoolForUpdate);
        assertEquals(actualAddress.getCity(),"Wrocław");
        assertEquals(actualAddress.getPostalCode(),"22-111");
        assertEquals(actualAddress.getStreet(),"Krótka");
        assertEquals(actualAddress.getBlockNumber(),"A");
        assertEquals(actualAddress.getApartmentNumber(),"B");
    }
    @Test
    @DisplayName("should give notification that school doesn't exist")
    void updateSchoolWithWrongSchoolName() {
        //given
        String wrongSchoolName = "uLuisa";
        Address newAddress = new Address.Builder()
                .city("Wrocław")
                .postalCode("22-111")
                .street("Krótka")
                .blockNumber("A")
                .apartmentNumber("B")
                .build();
        //when
        String actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .updateSchool(wrongSchoolName,newAddress);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should give notification that school doesn't exist even if we put null as name")
    void updateSchoolWithNullSchoolName() {
        //given
        String wrongSchoolName = null;
        Address newAddress = new Address.Builder()
                .city("Wrocław")
                .postalCode("22-111")
                .street("Krótka")
                .blockNumber("A")
                .apartmentNumber("B")
                .build();
        //when
        String actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .updateSchool(wrongSchoolName,newAddress);

        //than
        String expected = "No such school";
        assertEquals(expected, actual);
    }

    @Test
    void deleteSchool() {
    }

    @Test
    void iterateSchools() {
    }

    @Test
    @DisplayName("Should confirm that school is existing")
    void shouldConfirmThatSchoolIsExisting () {
        //given
        String schoolName = "szkoła specjalna";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool(schoolName,address);
        //when
        Boolean actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .isSchoolExisting(schoolName);
        //than
        assertTrue(actual);
    }
    @Test
    @DisplayName("Should confirm that shool is not existing")
    void shouldConfirmThatSchoolIsNotExisting () {
        //given
        String schoolName = "szkoła specjalna";
        String schoolWrongName = "uLuisa";
        Address address = new Address.Builder()
                .city("Kraków")
                .postalCode("30-818")
                .street("Długa")
                .blockNumber("3")
                .apartmentNumber("3")
                .build();
        MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .createSchool(schoolName,address);
        //when
        Boolean actual = MemoryBasedSchoolRepository.getMemoryBasedSchoolRepositoryInstance()
                .isSchoolExisting(schoolWrongName);
        //than
        assertFalse(actual);
    }
}