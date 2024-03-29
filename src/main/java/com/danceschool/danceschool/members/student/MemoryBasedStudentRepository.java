package com.danceschool.danceschool.members.student;

import com.danceschool.danceschool.data.Level;
import com.danceschool.danceschool.data.PersonalData;
import com.danceschool.danceschool.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MemoryBasedStudentRepository implements StudentRepository {
    private List<Student> studentList = new ArrayList<>();

    private static final MemoryBasedStudentRepository MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE =
            new MemoryBasedStudentRepository();

    private MemoryBasedStudentRepository() {
    }

    public static MemoryBasedStudentRepository getMemoryBasedStudentRepositoryInstance() {
        return MEMORY_BASED_STUDENT_REPOSITORY_INSTANCE;
    }

    @Override
    public UUID createStudent(PersonalData personalData, Level level) throws IOException {
        Student student = new Student.Builder(personalData)
                .level(level)
                .build();
        studentList.add(student);
        UUID uuid = student.getId();
        return uuid;
    }


    @Override
    public Student readStudent(UUID uuid) {
        int i = 0;
        while (i < studentList.size() && !studentList.get(i).getId().equals(uuid)) {
            ++i;
            System.out.println(i);
        }
        if (i >= studentList.size()) {
            System.out.println("user with uuid: " + uuid.toString() + " doesn't exist");
            throw new UserNotFoundException("user with uuid: " + uuid.toString() + " doesn't exist");
        } else {
            return studentList.get(i);
        }
    }

    @Override
    public Student updateStudent(UUID uuid, PersonalData newPersonalData, Level newLevel) {
        Student newStudent = new Student.Builder()
                .personalData(newPersonalData)
                .level(newLevel)
                .build();
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getId().equals(uuid)) {
                System.out.println("old student: " + student.toString());
                studentList.set(i, newStudent);
                System.out.println("new student: " + newStudent.toString());
            }
        }
        return null;
    }

    @Override
    public UUID deleteStudent(UUID uuid) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getId().equals(uuid)) {
                studentList.remove(i);
            }
        }
        return uuid;
    }

    public List<Student> getStudentList() {
        List<Student> studentListCopy = new ArrayList<>();
        studentListCopy.addAll(studentList);
        return studentListCopy;
    }

    public void deleteAllStudents() {
        studentList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryBasedStudentRepository that = (MemoryBasedStudentRepository) o;

        return studentList != null ? studentList.equals(that.studentList) : that.studentList == null;
    }

    @Override
    public int hashCode() {
        return studentList != null ? studentList.hashCode() : 0;
    }

    public boolean searchUUID(UUID uuid, List<Student> list) {
        boolean result;
        if (result = true) {
            for (int i = 0; i < list.size(); i++) {
                result = list.get(i).getId().equals(uuid);
            }
            return result;
        }
        return result;
    }
}
