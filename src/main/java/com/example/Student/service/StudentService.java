package com.example.Student.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Student.model.Student;
import com.example.Student.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    public List<Student> searchStudents(String keyword){
        return repo.findByNameContainingIgnoreCase(keyword);
    }
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
    public void saveStudent(Student student) {
        repo.save(student);
    }
    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }
    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}