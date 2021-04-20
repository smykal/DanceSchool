package com.danceschool.danceschool.groups;

import static org.mockito.Mockito.when;
import com.danceschool.danceschool.members.Members;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBasedGroupRepositoryTest {

    public static final MemoryBasedGroupRepository MEMORY_BASED_GROUP_REPOSITORY_INSTANCE = MemoryBasedGroupRepository
            .getMemoryBasedGroupRepositoryInstance();
    public static final String GROUP = "group";

    @Mock
    Members member;

    @Mock
    Members member2;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(GROUP);
    }

    @AfterEach
    public void cleanUpEach() {
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .clear();
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("methods after all tests");
    }

    @Test
    void shouldGenerateNewGroupAndIncreasieHashMapSize() {
        //given
        String groupName = "advanced group";
        int actualSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .size();

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //than
        int expectedSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .size();
        assertEquals(actualSize+1,expectedSize);
    }

    @Test
    void shouldCreateNewGroupInGroupsList() {
        //given
        String groupName = "medium Group";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //than
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .containsKey(groupName);
        assertTrue(expectedTrue);
    }

    @Test
    void shouldCreateNewListInGroupNamedAmateurGroup() {
        //given
        String groupName = "amateur group";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //than
        int expectedSize = 0;
        int actualSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(groupName)
                .size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    void shouldCreateNewGroupWithListWhichIsEmpty() {
        //given
        String groupName = "professional group";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //than
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(groupName)
                .isEmpty();
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnStringNicWhenAskForNonExistingGroup() {
        //given
        String wrongGroupName = "wrong group name";

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(wrongGroupName);

        //than
        String expected = "nic";
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnStringNicWhenAskForNullGroup() {
        //given

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(null);
        String expected = "nic";

        //than
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnThatGroupIsEmpty() {
        //given

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(GROUP);
        String expected = "Grupa o nazwie: group is empty";

        //than
        assertEquals(expected,actual);
    }

    @Test
        void shouldReturnMembersInsideGroupList() {
        //given
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member2);

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(GROUP);

        //than
        String expected = "\nmember\nmember2";
        assertEquals(expected,actual);
    }

    @Test
    void shouldSetNewGroupNameIntoGroupsList() {
        //given
        String newName = "newGroup";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(GROUP, newName);
        boolean expectTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .containsKey(newName);
        //than
        assertTrue(expectTrue);
    }

    @Test
    void shouldRemoveOldGroupNameFromGroupsList() {
        //given
        String newName = "newGroup";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(GROUP, newName);
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .containsKey(GROUP);

        //than
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnFalseWhenTryToUpdateGroupWhichDoesNotExist() {
        //given
        String groupWhichDoesNotExist = "unexisting group";

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(groupWhichDoesNotExist, GROUP);

        //than
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnFalseWhenTryToUpdateGroupNamedNull() {
        //given

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(null, GROUP);

        //than
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnHashMap() {
        //given

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .getClass()
                .getSimpleName();

        //than
        String expected = "HashMap";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDisplayListOfGroups() {
        //given
        Map map = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups();

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .iterateGroups(map);

        //than
        String expected = "Wykaz grup: \n" + GROUP + "\n";
        assertEquals(expected,actual);
    }

    @Test
    void shouldDisplayGroupMembersFromGroupList() {
        //given
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member2);
        ArrayList<Members> list = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups().get(GROUP);

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .iterateGroupMembers(list);

        //than
        String expected = "\nmember\nmember2";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFalseWhenGroupDoesNotExist() {
        //given
        String groupWhichDoesNotExist = "Non existing group";

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(groupWhichDoesNotExist);

        //than
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWhenGroupExist() {
        //given
        String correctKeyValue = GROUP;

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(correctKeyValue);

        //than
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullValue() {
        //given

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(null);
        //than
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnFalseWithWrongGroupName() {
        //given
        String invalidGroupName = "wrong name";

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(invalidGroupName, member);

        //than
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWithCorrectGroupName(){
        //given

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP, member);

        //than
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullAsGroupName() {
        //given

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(null, member);

        //than
        assertFalse(expectFalse);
    }

    @Test
    void shouldIncreaseGroupListSize() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member);


        //than
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(GROUP)
                .size();
        assertEquals(1, actual);
    }

    @Test
    void shouldIncreaseGroupListSizeTo2() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP, member);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP, member2);

        //than
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(GROUP)
                .size();
        assertEquals(2, actual);
    }

    @Test
    void shouldContainMembersInGroupList() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member2);

        //than
        boolean expectedMember = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(GROUP)
                .contains(member);
        boolean expectedMember2 = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroups()
                .get(GROUP)
                .contains(member2);
        assertTrue(expectedMember);
        assertTrue(expectedMember2);
    }
}