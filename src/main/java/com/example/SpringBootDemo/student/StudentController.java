package com.example.SpringBootDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    @PostMapping()
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentEmail}")
    public void removeStudent(@PathVariable("studentEmail") String email)
    {
        studentService.deleteStudent(email);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email)
    {
        studentService.updateStudent(id, name, email);
    }
}