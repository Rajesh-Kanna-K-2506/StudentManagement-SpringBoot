package com.example.Student.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.Student.model.Student;
import com.example.Student.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;
    
    @GetMapping("/test")
    public String test() {
        System.out.println("TEST MAPPING WORKS");
        return "index";
    }
    
    @GetMapping("/search")
    public String searchStudent(
            @RequestParam("keyword") String keyword,
            Model model) {
    	System.out.println("SEARCH = " + keyword);
        model.addAttribute(
                "students",
                service.searchStudents(keyword));
        return "index";
    }
    
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students",
                service.getAllStudents());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student",
                new Student());
        return "addStudent";
    }

    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute("student") Student student) {
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable int id,
            Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/update")
    public String updateStudent(
            @ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/";
    }
}