package kg.tarantula.dao;

import kg.tarantula.models.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {
    private static int studentId;
    private List<Student> studentList;
    {
        studentList = new ArrayList<>();
        studentList.add(new Student(++studentId,"Abay"));
        studentList.add(new Student(++studentId, "Nurtas"));
        studentList.add(new Student(++studentId, "Nuskayim"));
    }

    public List<Student> index() {
        return studentList;
    }

    public Student show(int id) {
        return studentList.stream().filter(student -> student.getId() == id).findAny().orElse(null);
    }

    public void save(Student student) {
        student.setId(++studentId);
        studentList.add(student);
    }

    public void update(int id, Student updatedStudent) {
        Student studentToBeUpdated = show(id);
        studentToBeUpdated.setName(updatedStudent.getName());
    }

    public void delete(int id) {
        studentList.removeIf(s -> s.getId() == id);
    }
}
