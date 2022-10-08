package kg.tarantula.dao;

import kg.tarantula.models.Student;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {
    private static int studentId;
    private List<Student> studentList;

    private static final String url = "jdbc:postgresql://localhost:5432/first_db";
    private static final String user = "postgres";
    private static final String password = "admin";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> index() throws SQLException {
        List<Student> students = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM  student");
        while(resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setEmail(resultSet.getString("email"));

            students.add(student);
        }

        return students;

    }

    public Student show(int id) {
        //return studentList.stream().filter(student -> student.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Student student) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into student values(" + 1 + ", '" + student.getName() + "', " +
                "'" + student.getAge() + "', '" + student.getEmail() + "')");
    }

    public void update(int id, Student updatedStudent) {
//        Student studentToBeUpdated = show(id);
//        studentToBeUpdated.setName(updatedStudent.getName());
//        studentToBeUpdated.setAge(updatedStudent.getAge());
//        studentToBeUpdated.setEmail(updatedStudent.getEmail());
    }

    public void delete(int id) {
        //studentList.removeIf(s -> s.getId() == id);
    }
}
