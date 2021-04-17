package com.danceschool.danceschool.groups;

import com.danceschool.danceschool.members.Members;

import java.util.*;

public class MemoryBasedGroupRepository implements GroupRepository {
    Map<String, List<Members>> groups = new HashMap<>();

    private static MemoryBasedGroupRepository memoryBasedGroupRepository = new MemoryBasedGroupRepository();

    private MemoryBasedGroupRepository() {}

    public static MemoryBasedGroupRepository getMemoryBasedGroupRepositoryInstance() {
        return memoryBasedGroupRepository;
    }

    @Override
    public String createGroup(String groupName) {
        ArrayList<Members> list = new ArrayList<>();
        groups.put(groupName, list);
        StringBuffer display = new StringBuffer();
        display.append("Dodano grupę o nazwie: " + groupName + "\n");
        System.out.println(display);
        return display.toString();
    }

    @Override
    public String readGroup(String groupName) {
        if (isGroupExisting(groupName)) {
            List<Members> groupMembers = MemoryBasedGroupRepository
                    .getMemoryBasedGroupRepositoryInstance()
                    .getGroups()
                    .get(groupName);
            if (groupMembers == null) {
                System.out.println("Grupa o nazwie: " + groupName + " is empty");
                return "Grupa o nazwie: " + groupName + " is empty";
            } else {
                System.out.println(iterateGroupMembers(groupMembers));
                return iterateGroupMembers(groupMembers);
            }
        } else {
            System.out.println("Grupa o nazwie: " + groupName + " nie istnieje");
        }
        return "nic ";
    }

    @Override
    public String updateGroup(String groupName, Members member) {
        if (isGroupExisting(groupName)) {
            MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                    .groups.get(groupName);
            return "git";
        }
        return "nie ma takiej grupy";
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

    public String iterateGroupMembers(List<Members> membersList) {
        StringBuffer display = new StringBuffer();
        Iterator<Members> listOfMembersIterator = membersList.iterator();

        while(listOfMembersIterator.hasNext()) {
            display.append(listOfMembersIterator.next());
        }
        System.out.println(display);
        return display.toString();
    }
    public boolean isGroupExisting(String groupName) {
        boolean result = MemoryBasedGroupRepository
                .getMemoryBasedGroupRepositoryInstance()
                .groups
                .containsKey(groupName);
        return result;
    }

    public boolean addMemberToGroup(String groupName, Members member) {
        if (isGroupExisting(groupName)) {
            MemoryBasedGroupRepository.getMemoryBasedGroupRepositoryInstance()
                    .groups
                    .get(groupName)
                    .add(member);
            System.out.println("success");
            return true;
        } else
        return false;
    }
}
