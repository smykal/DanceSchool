package com.danceschool.danceschool.groups;

import static org.mockito.Mockito.when;

import com.danceschool.danceschool.exceptions.GroupNotFoundException;
import com.danceschool.danceschool.exceptions.SchoolNotFoundException;
import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;
import com.danceschool.danceschool.members.teacher.Teacher;
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
    public void initEach() {
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
        assertEquals(actualSize + 1, expectedSize);
    }

    @Test
    void shouldCreateNewGroupInGroupsList() {
        //given
        String groupName = "medium Group";


        //when
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList();

        //then
        boolean isGroupExisting = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.isGroupExisting(groupUUID, groupsList);

        assertTrue(isGroupExisting);
    }

    @Test
    void shouldCreateNewListInGroupNamedAmateurGroup() {
        //given
        String groupName = "amateur group";

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        int expectedSize = 2;
        int actualSize = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void shouldCreateNewGroupWithStudentListWhichIsEmpty() {
        //given
        String groupName = "professional group";

        //when
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        boolean expectedTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(groupUUID)
                .getGroupStudentList()
                .isEmpty();
        assertTrue(expectedTrue);
    }

    @Test
    void shouldCreateNewGroupWithGroupFemaleTeacherWhichIsEmpty() {
        //given
        String groupName = "professional group";

        //when
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        Teacher expectedTeacher = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(groupUUID)
                .getGroupFemaleTeacher();
        assertNull(expectedTeacher);
    }

    @Test
    void shouldCreateNewGroupWithGroupMaleTeacherWhichIsEmpty() {
        //given
        String groupName = "professional group";

        //when
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .createGroup(groupName);

        //then
        Teacher expectedTeacher = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(groupUUID)
                .getGroupMaleTeacher();
        assertNull(expectedTeacher);
    }

    @Test
    void shouldReturnExceptionGroupNotFound() {
        //given
        UUID uuid = UUID.randomUUID();

        //when //then
        assertThrows(GroupNotFoundException.class,
                () -> {
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
        assertEquals(actual.getClass(), Group.class);
    }


    @Test
    void shouldSetNewGroupNameIntoGroupsList() {
        //given
        String newName = "newGroup";
        UUID groupUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup(GROUP);

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .updateGroupName(groupUUID, newName);

        String expectedName = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .readGroup(groupUUID)
                .getGroupName();
        //then
        assertEquals(expectedName, newName);
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

    @Test
    void shouldCatchExceptionWhenTryToUpdateGroupWhichDoesNotExist() {
        //given
        UUID groupWhichDoesNotExist = UUID.randomUUID();

        //when

        //then
        assertThrows(GroupNotFoundException.class,
                () -> {
                    MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                            .updateGroupName(groupWhichDoesNotExist, GROUP);
                    //do whatever you want to do here
                    //ex : objectName.thisMethodShoulThrowNullPointerExceptionForNullParameter(null);
                });
    }

    @Test
    void shouldReturnList() {
        //given

        //when
        String actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .getClass()
                .getSimpleName();

        //then
        String expected = "ArrayList";
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
        UUID groupId = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().get(0).getGroupId();

        //then
        String expected = "Wykaz grup: \n"
                + groupId.toString() + " group null null ";
        assertEquals(expected, actual);
    }

    @Test
    void shouldDisplayGroupMembersFromGroupList() {
        //given
        Group group = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().get(0);
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(group);
        List<Student> groupStudentList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(indexOfGroup)
                .getGroupStudentList();
        groupStudentList
                .add(student);
        groupStudentList
                .add(student2);

        //when
        StringBuffer actual = new StringBuffer();
        for (int i = 0; i < groupStudentList.size(); i++) {
            actual.append(groupStudentList.get(i).toString() + "\n");
        }

        //then
        String expected = "student\nstudent2\n";
        boolean contains = actual.toString().contains(expected);
        assertTrue(contains);
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
    void shouldReturnTrueWithCorrectGroupName() {
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
    void shouldReturnTrueWhenAddStudentToStudentList() {
        //given

        //when
        boolean expectTrue = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(student);

        //then
        assertTrue(expectTrue);
    }

    @Test
    void shouldReturn2WhenAddStudentAndStudent2ToStudentList() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(student);


        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .add(student);

        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .get(0)
                .getGroupStudentList()
                .size();

        //then
        assertEquals(2, actual);
    }

    @Test
    void shouldIncreaseGroupListSize() {
        //given

        //when
        MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");

        //then
        int actual = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList()
                .size();
        assertEquals(2, actual);
    }

    @Test
    void shouldIncreaseGroupListSizeTo2() {
        //given
        UUID groupNameUUID = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.createGroup("groupName");
        Group groupToRead = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.readGroup(groupNameUUID);
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(groupToRead);

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
        Group group = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.readGroup(groupNameUUID);
        int indexOfGroup = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList().indexOf(group);


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