package com.danceschool.danceschool.groups;

import static org.mockito.Mockito.when;

import com.danceschool.danceschool.exceptions.GroupNotFoundException;
import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Mock
    Student student;

    @Mock
    Student student2;

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
    void shouldGenerateNewGroupAndIncreaseHashMapSize() {
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
    void shouldSetNewGroupNameIntoGroupsList() {
        //given
        String newName = "newGroup";
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup(GROUP);

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(groupUUID, newName);
        boolean expectTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .contains(newName);
        //then
        assertTrue(expectTrue);
    }

    @Test
    void shouldRemoveOldGroupNameFromGroupsList() {
        //given
        String newName = "newGroup";
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup(newName);
        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(groupUUID, newName);
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .contains(groupUUID);

        //then
        assertFalse(expectFalse);
    }

//    @Test
//    void shouldReturnFalseWhenTryToUpdateGroupWhichDoesNotExist() {
//        //given
//        UUID groupWhichDoesNotExist = UUID.randomUUID();
//
//        //when
//        Group expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
//                .updateGroupName(groupWhichDoesNotExist, GROUP);
//
//        //then
//        assertFalse(expectFalse);
//    }

    @Test
    void shouldReturnFalseWhenTryToUpdateGroupNamedNull() {
        GroupNotFoundException thrown = assertThrows(
                GroupNotFoundException.class,
                () -> MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                        .updateGroupName(null, GROUP),
                "Expected doThing() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("group with id: " ));
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
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList();

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .iterateGroups(groupsList);

        //then
        String expected = "Wykaz grup: \n" + GROUP + "\n";
        assertEquals(expected,actual);
    }

    @Test
    void shouldDisplayGroupMembersFromGroupList() {
        //given
        UUID groupNameUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(groupNameUUID);
        List<Student> groupStudentList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList();
        groupStudentList
                .add(student);
        groupStudentList
                .add(student2);




        //when
        String actual = groupStudentList.stream().iterator().toString();

        //then
        String expected = "\nmember\nmember2";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFalseWhenGroupDoesNotExist() {
        //given
        String groupWhichDoesNotExist = "Non existing group";
        UUID randomUUID = UUID.randomUUID();
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList();

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(randomUUID, groupsList);

        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWhenGroupExist() {
        //given
        UUID existingGroupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("existingGroup");
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList();

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(existingGroupUUID, groupsList);

        //then
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullValue() {
        //given
        UUID uuid = UUID.randomUUID();
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList();
        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .isGroupExisting(uuid, groupsList);
        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnFalseWithWrongGroupName() {
        //given
        String invalidGroupName = "wrong name";

        //when
        boolean expectedFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(student);

        //then
        assertFalse(expectedFalse);
    }

    @Test
    void shouldReturnTrueWithCorrectGroupName(){
        //given

        //when
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(student2);

        //then
        assertTrue(expectedTrue);
    }

    @Test
    void shouldReturnFalseWithNullAsGroupName() {
        //given

        //when
        boolean expectFalse = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(null);

        //then
        assertFalse(expectFalse);
    }

    @Test
    void shouldIncreaseGroupListSize() {
        //given

        //when
        UUID groupName = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");



        //then
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .size();
        assertEquals(1, actual);
    }

    @Test
    void shouldIncreaseGroupListSizeTo2() {
        //given
        UUID groupNameUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(groupNameUUID);
        Group group = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().get(indexOfGroup);
        //when
        group.getGroupStudentList().add(student);
        group.getGroupStudentList().add(student2);

        //then
        int actual = group
                .getGroupStudentList()
                .size();
        assertEquals(2, actual);
    }

    @Test
    void shouldContainMembersInGroupList() {
        //given
        UUID groupNameUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(groupNameUUID);


        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList()
                .add(student);
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList()
                .add(student2);


        //then
        boolean expectedStudent = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList()
                .contains(student);
        boolean expectedStudent2 = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList()
                .contains(student2);
        assertTrue(expectedStudent);
        assertTrue(expectedStudent2);
    }
}