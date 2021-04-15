package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Groups {
    Map<String, List<Members>> groups = new HashMap<>();

    private static Groups danceSchoolGroups = new Groups();

    private Groups() {}

    public static Groups getDanceSchoolGroups() {
        return danceSchoolGroups;
    }
}

