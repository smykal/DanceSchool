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

    public String iterateGroup(String groupName) {
            List<Members> members = groups.get(groupName);
            StringBuffer display = new StringBuffer();
            display.append("Members of group: " + groupName + "\n");
        if (members == null) {
            System.out.println("Grupa jest pusta");
        } else {
            for (int i = 0; i < members.size(); i++) {
                display.append(members.get(i).toString() + " \n");
            }
        }
        return display.toString();
    }
}

