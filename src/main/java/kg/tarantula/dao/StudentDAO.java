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
        studentList.add(new Student(++studentId,"Abay",1, "abay@mail.com"));
        studentList.add(new Student(++studentId, "Nurtas",1,"nurtas@mail.com"));
        studentList.add(new Student(++studentId, "Nuskayim",2,"nusk@mail.com"));
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
        studentToBeUpdated.setAge(updatedStudent.getAge());
        studentToBeUpdated.setEmail(updatedStudent.getEmail());
    }

    public void delete(int id) {
        studentList.removeIf(s -> s.getId() == id);
    }
}
