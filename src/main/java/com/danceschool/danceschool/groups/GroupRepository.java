package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;

public interface GroupRepository {
    String createGroup(String groupName);
    String readGroup(String groupName);
    String updateGroup(String groupName, Members member);
}
