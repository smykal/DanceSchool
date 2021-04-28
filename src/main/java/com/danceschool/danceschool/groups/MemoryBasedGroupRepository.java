package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.exceptions.GroupNotFoundException;
import com.danceschool.danceschool.members.Members;

import java.util.*;

public class MemoryBasedGroupRepository implements GroupRepository {
    public static final MemoryBasedGroupRepository MEMORY_BASED_GROUP_REPOSITORY_INSTANCE = MemoryBasedGroupRepository
            .getMemoryBasedGroupRepositoryInstance();
    private List<Group> groupsList = new ArrayList<>();

    private static MemoryBasedGroupRepository memoryBasedGroupRepository = new MemoryBasedGroupRepository();

    private MemoryBasedGroupRepository() {
    }

    public static MemoryBasedGroupRepository getMemoryBasedGroupRepositoryInstance() {
        return memoryBasedGroupRepository;
    }

    @Override
    public UUID createGroup(String groupName) {
        Group group = new Group.Builder()
                .groupName(groupName)
                .build();
        group.setGroupId(UUID.randomUUID());
        groupsList.add(group);
        return group.getGroupId();
    }

    @Override
    public Group readGroup(UUID uuid) {
        List<Group> groupsList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE
                .getGroupsList();
        if (!isGroupExisting(uuid, groupsList)) {
            throw new GroupNotFoundException("group not found");
        } else {
            return findGroup(uuid, groupsList);
        }
    }



    @Override
    public Group updateGroupName(UUID uuid, String newGroupName) {
        List<Group> groupList = MEMORY_BASED_GROUP_REPOSITORY_INSTANCE.getGroupsList();
        if (!isGroupExisting(uuid, groupsList)) {
            throw new GroupNotFoundException("group not found");
        } else {
            findGroup(uuid,groupsList).setGroupName(newGroupName);
            return findGroup(uuid, groupsList);
        }
    }

    public List<Group> getGroupsList() {
        return groupsList;
    }

    public String iterateGroups(Map<String, List<Members>> values) {
        StringBuffer display = new StringBuffer();
        display.append("Wykaz grup: \n");

        for (Map.Entry<String, List<Members>> entry : values.entrySet()) {
            display.append(entry.getKey() + "\n");
        }
        System.out.println(display);
        return display.toString();
    }

    public String iterateGroupMembers(List<Members> membersList) {
        StringBuffer display = new StringBuffer();
        Iterator<Members> listOfMembersIterator = membersList.iterator();

        while (listOfMembersIterator.hasNext()) {
            display.append("\n" + listOfMembersIterator.next());
        }
        return display.toString();
    }
    private Group findGroup(UUID uuid, List<Group> groupsList) {
        for (int i = 0; i < groupsList.size(); i++) {
            if (groupsList.get(i).getGroupId().equals(uuid)) {
                return groupsList.get(i);
            }
        }
        return null;
    }
    public boolean isGroupExisting(UUID uuid, List<Group> groupsList) {
        boolean result = false;
        for (int i = 0; i < groupsList.size(); i++) {
            if (groupsList.get(i).getGroupId().equals(uuid)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
