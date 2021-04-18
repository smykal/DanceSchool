package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.teacher.Teacher;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedGroupRepositoryTest {

    @Mock
    Members member;
    Members member2;

    @BeforeAll
    public static void init(){
        System.out.println("methods before all tests");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("\n\nmethods before each test");
        String groupName = "group";
        MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .createGroup(groupName);
    }

    @AfterEach
    public void cleanUpEach() {
        System.out.println("methods after each test");
        String groupName = "group";
        MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .clear();
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("methods after all tests");
    }

    @Test
    @DisplayName("createGroup() - should add new group into groups")
    void createGroupAndIncreaseHashMapSize() {
        //given
        String groupName = "advanced group";
        int actualSize = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups.size();

        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup(groupName);

        //than
        int expectedSize = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .size();
        assertEquals(actualSize+1,expectedSize);
    }

    @Test
    @DisplayName("createGroup() - should add Key to HashMap")
    void createGroupAndAddNewKey() {
        //given
        String groupName = "medium Group";

        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup(groupName);

        //than
        boolean expectedTrue = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .groups
                .containsKey(groupName);
        assertTrue(expectedTrue);
    }

    @Test
    @DisplayName("createGroup() - should add Value >0-size-list< into HashMap")
    void createGroupAndAddNewValue() {
        //given
        String groupName = "amateur group";
        int expectedSize = 0;
        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup(groupName);

        //than
        int actualSize = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .get(groupName)
                .size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    @DisplayName("createGroup() - should add empty List as a value to HashMap")
    void createGroupAndAddEmptyListValue() {
        //given
        String groupName = "professional group";

        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .createGroup(groupName);

        //than
        boolean expectedTrue = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .get(groupName)
                .isEmpty();
        assertTrue(expectedTrue);
    }

    @Test
    @DisplayName("readGroup() - with wrong group name should return String \"nic\" ")
    void readGroupWithWrongGroupName() {
        //given
        String wrongGroupName = "wrong group name";
        //when
        String actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .readGroup(wrongGroupName);
        String expected = "nic";

        //than
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("readGroup() - with null group name should return String \"nic\" ")
    void readGroupWithNullGroupName() {
        //given

        //when
        String actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .readGroup(null);
        String expected = "nic";

        //than
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("readGroup() - with correct group name should return String \"Grupa o nazwie: group is empty\" ")
    void readGroupWithCorrectGroupName() {
        //given
        String correctGroupName = "group";
        //when
        String actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .readGroup(correctGroupName);
        String expected = "Grupa o nazwie: group is empty";

        //than
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("readGroup() - with correct group name should return Members of group")
    void readGroupWithCorrectGroupNameAndMembers() {
        //given
        String correctGroupName = "group";
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(correctGroupName,member);
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(correctGroupName,member2);
        //when
        String actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .readGroup(correctGroupName);
        String expected = "Grupa o nazwie: group is empty";

        //than
        assertNotEquals(expected,actual);
    }

    @Test
    @DisplayName("updateGroup() - changeGroupName when group exist")
    void updateExistingGroupName() {
        //given
        String oldName = "group";
        String newName = "newGroup";

        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .updateGroupName(oldName, newName);
        boolean expectTrue = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .containsKey(newName);
        //than
        assertTrue(expectTrue);
    }

    @Test
    @DisplayName("updateGroup() - changeGroupName when group doesn't exist")
    void updateUnexistingGroupName() {
        //given
        String oldName = "unexisting group";
        String newName = "newGroup";

        //when
        boolean expectFalse = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .updateGroupName(oldName, newName);

        //than
        assertFalse(expectFalse);
    }

    @Test
    @DisplayName("updateGroup() - changeGroupName when group name is null")
    void updateNullGroupName() {
        //given

        //when
        boolean expectFalse = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .updateGroupName(null, null);

        //than
        assertFalse(expectFalse);
    }

    @Test
    @DisplayName("getGroups() - should return Map<String, List<Members>>")
    void getGroups() {
        //given

        //when
        Map objectActual = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .getGroups();

        //than
        assertNotNull(objectActual);
    }

    @Test
    @DisplayName("iterateGroups() - should display list of groups")
    void iterateGroupsTest() {
        //given
        Map map = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .getGroups();

        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .iterateGroups(map);

        //than
        String expected = "Wykaz grup: \n" + "group" + "\n";
        String actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .iterateGroups(map);
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("iterateGroupMembers() - should display group members list")
    void iterateGroupMembersTest() {
        //given
        String groupName = "group";
        List list = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .getGroups().get(groupName);
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(groupName,member);
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(groupName,member2);
        //when
        String actual =MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .iterateGroupMembers(list);

        //than
        String expected = "null\nnull\n";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isGroupExisting() - with wrong Key-value should give back false value")
    void isGroupExistingWithWrongKeyValue() {
        //given
        String wrongKeyValue = "random";

        //when
        boolean expectedFalse = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .isGroupExisting(wrongKeyValue);

        //than
        assertFalse(expectedFalse);
    }

    @Test
    @DisplayName("isGroupExisting() - with correct Key-value should give back true value")
    void isGroupExistingWithCorrectKeyValue() {
        //given
        String correctKeyValue = "group";

        //when
        boolean expectedTrue = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .isGroupExisting(correctKeyValue);

        //than
        assertTrue(expectedTrue);
    }

    @Test
    @DisplayName("isGroupExisting() - with null value should give back false")
    void isGroupExistingWithNullValue() {
        //given

        //when
        boolean expectedFalse = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .isGroupExisting(null);
        //than
        assertFalse(expectedFalse);
    }


    @Test
    @DisplayName("addMemberToGroup - with invalid group name should return false")
    void addMemberToInvalidGroupName() {
        //given
        String invalidGroupName = "wrong name";

        //when
        boolean expectedFalse = MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(invalidGroupName, member);

        //than
        assertFalse(expectedFalse);
    }

    @Test
    @DisplayName("addMemberToGroup - with correct group should return true")
    void addMemberToGroupWithCorrectGroupName(){
        //given
        String correctGroupName = "group";

        //when
        boolean expectedTrue = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(correctGroupName, member);

        //than
        assertTrue(expectedTrue);
    }

    @Test
    @DisplayName("addMemberToGroup() - with null as a group name should return false")
    void addMemberToGroupWithNullAsGroupName() {
        //given

        //when
        boolean expectFalse = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(null, member);

        //than
        assertFalse(expectFalse);
    }

    @Test
    @DisplayName("addMemberToGroup() - should add member to group list")
    void addMemberToGroupAndIncreaseGroupSize() {
        //given
        String correctGroupName = "group";
        //when
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(correctGroupName,member);
        MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                .addMemberToGroup(correctGroupName,member2);

        //than
        int actual = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .get(correctGroupName)
                .size();
        assertEquals(2,actual);
    }
}