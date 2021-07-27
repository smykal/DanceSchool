package com.danceschool.danceschool.groups;

import static org.mockito.Mockito.when;

import com.danceschool.danceschool.exceptions.GroupNotFoundException;
import com.danceschool.danceschool.members.Members;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

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
                .getGroupsList()
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
                .getGroupsList()
                .size();

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        int expectedSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
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

        //then
        for (int i = 0; i < MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().size(); i++) {
            if (MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().get(i).getGroupName().equals(groupName)) {
                Assertions.assertTrue(true);
            }
        }
        Assertions.fail();
    }

    @Test
    void shouldCreateNewListInGroupNamedAmateurGroup() {
        //given
        String groupName = "amateur group";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        int expectedSize = 0;
        int actualSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
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

        //then
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .isEmpty();
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnExceptionGroupNotFound() {
        //given
        UUID uuid = UUID.randomUUID();

        //when //then
        assertThrows(GroupNotFoundException.class,
                ()->{
                    MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                            .readGroup(uuid);
                });

       }


    @Test
    void shouldReturnThatGroupIsEmpty() {
        //given
        UUID uuid = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("anyName");
        //when
        Group actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(uuid);

        //then
        assertEquals(actual.getClass(),Group.class);
    }

    @Test
        void shouldReturnMembersInsideGroupList() {
        //given
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .setGroupStudentListStudent()
                .addMemberToGroup(GROUP,member);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member2);

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(GROUP);

        //then
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
                .getGroupsList()
                .containsKey(newName);
        //then
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
                .getGroupsList()
                .containsKey(GROUP);

        //then
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnFalseWhenTryToUpdateGroupWhichDoesNotExist() {
        //given
        String groupWhichDoesNotExist = "unexisting group";

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(groupWhichDoesNotExist, GROUP);

        //then
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnFalseWhenTryToUpdateGroupNamedNull() {
        //given

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(null, GROUP);

        //then
        assertFalse(expectFalse);
    }

    @Test
    void shouldReturnHashMap() {
        //given

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .getClass()
                .getSimpleName();

        //then
        String expected = "HashMap";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDisplayListOfGroups() {
        //given
        Map map = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList();

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .iterateGroups(map);

        //then
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
                .getGroupsList().get(GROUP);

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .iterateGroupMembers(list);

        //then
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

        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWhenGroupExist() {
        //given
        String correctKeyValue = GROUP;

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(correctKeyValue);

        //then
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullValue() {
        //given

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(null);
        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnFalseWithWrongGroupName() {
        //given
        String invalidGroupName = "wrong name";

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(invalidGroupName, member);

        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWithCorrectGroupName(){
        //given

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP, member);

        //then
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullAsGroupName() {
        //given

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(null, member);

        //then
        assertFalse(expectFalse);
    }

    @Test
    void shouldIncreaseGroupListSize() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .addMemberToGroup(GROUP,member);


        //then
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
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

        //then
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
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

        //then
        boolean expectedMember = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(GROUP)
                .contains(member);
        boolean expectedMember2 = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(GROUP)
                .contains(member2);
        assertTrue(expectedMember);
        assertTrue(expectedMember2);
    }
}