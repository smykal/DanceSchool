package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;

public interface GroupRepository {
    String createGroup(String groupName);
    String readGroup(String groupName);
    boolean updateGroupName(String groupName, String newGroupName);
}
