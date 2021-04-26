package com.danceschool.danceschool.groups;

public interface GroupRepository {
    String createGroup(String groupName); // TODO UUID
    String readGroup(String groupName); // TODO group
    boolean updateGroupName(String groupName, String newGroupName);
    // TODO deleteGroup() : boolean
}
