package com.example.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Student.model.Student;
import com.example.Student.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

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

        Student student =
                service.getStudentById(id);

        model.addAttribute("student",
                student);

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