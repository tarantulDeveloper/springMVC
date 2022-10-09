package kg.tarantula.dao;

import kg.tarantula.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {
    private static int studentId;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Student> index() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM student", new StudentMapper());
    }

    public Student show(int id) {
        return jdbcTemplate.query("SELECT * FROM student WHERE id=?", new Object[]{id}, new StudentMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Student student) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO student values(1, ?, ?, ?)"
//        );
//        preparedStatement.setString(1, student.getName());
//        preparedStatement.setInt(2, student.getAge());
//        preparedStatement.setString(3, student.getEmail());
//
//        preparedStatement.executeUpdate();
    }

    public void update(int id, Student updatedStudent) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE student SET name=?, age=?, email=? WHERE id=?"
//            );
//
//            preparedStatement.setString(1, updatedStudent.getName());
//            preparedStatement.setInt(2, updatedStudent.getAge());
//            preparedStatement.setString(3, updatedStudent.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "DELETE FROM student WHERE id=?"
//            );
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
