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
        while (resultSet.next()) {
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
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM student WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            student = new Student();

            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            student.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public void save(Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO student values(1, ?, ?, ?)"
        );
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getAge());
        preparedStatement.setString(3, student.getEmail());

        preparedStatement.executeUpdate();
    }

    public void update(int id, Student updatedStudent) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE student SET name=?, age=?, email=? WHERE id=?"
            );

            preparedStatement.setString(1, updatedStudent.getName());
            preparedStatement.setInt(2, updatedStudent.getAge());
            preparedStatement.setString(3, updatedStudent.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM student WHERE id=?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
