package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.data.Address;
import com.danceschool.danceschool.members.Members;
import com.danceschool.danceschool.members.student.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MemoryBasedGroupRepository implements GroupRepository {
    Map<String, List<Members>> groups = new HashMap<>();

    private static MemoryBasedGroupRepository memoryBasedGroupRepository = new MemoryBasedGroupRepository();

    private MemoryBasedGroupRepository() {}

    public static MemoryBasedGroupRepository getMemoryBasedGroupRepositoryInstance() {
        return memoryBasedGroupRepository;
    }

    @Override
    public String createGroup(String groupName) {
        groups.put(groupName, null);
        StringBuffer display = new StringBuffer();
        display.append("Dodano grupę o nazwie: " + groupName + "\n");
        System.out.println(display);
        return display.toString();
    }

    @Override
    public String readGroup(String groupName) {
        List<Members> groupMembers = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .getGroups()
                .get(groupName);
        return iterateGroupMembers(groupMembers);
    }

    public Map<String, List<Members>> getGroups() {
        return groups;
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

    public String iterateGroupMembers(List<Members> addressOfMembers) {
        StringBuffer display = new StringBuffer();
        Iterator<Members> listOfMembersIterator = addressOfMembers.iterator();

        while(listOfMembersIterator.hasNext()) {
            display.append(listOfMembersIterator.next());
            display.append("\n");
        }
        System.out.println(display);
        return display.toString();
    }
}
