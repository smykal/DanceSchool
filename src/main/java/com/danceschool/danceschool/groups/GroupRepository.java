package com.danceschool.danceschool.groups;

import java.util.UUID;

public interface GroupRepository {
    UUID createGroup(String groupName);
    Group readGroup(UUID groupId);
    Group updateGroupName(UUID groupId, String newGroupName);
    boolean deleteGroup(UUID groupId);
}
