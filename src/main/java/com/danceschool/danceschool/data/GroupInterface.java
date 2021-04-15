package com.danceschool.danceschool.data;

import com.danceschool.danceschool.teacher.Teacher;

public interface GroupInterface {
    Group setUpNewGroup(String name);
    String addFemaleTeacher(Group group, Teacher teacher);
    String addMaleTeacher();
    String addFemaleStudent();
    String addMaleStudent();
}
