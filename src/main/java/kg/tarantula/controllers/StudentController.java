package kg.tarantula.controllers;

import kg.tarantula.dao.StudentDAO;
import kg.tarantula.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping()
    public String index(Model model) {
        //get all students
        model.addAttribute("students", studentDAO.index());
        return "student/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //get one student
        model.addAttribute("student", studentDAO.show(id));
        return "student/show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "student/new";
    }

    @PostMapping()
    public String createStudent(@ModelAttribute("student") Student student) {
        studentDAO.save(student);
        return "redirect:student";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentDAO.show(id));
        return "student/edit";
    }

    @PatchMapping("/{id}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable("id") int id) {
        studentDAO.update(id, student);
        return "redirect:/student";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentDAO.delete(id);
        return "redirect:/student";
    }



}
