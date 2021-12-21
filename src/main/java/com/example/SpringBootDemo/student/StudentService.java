package com.example.SpringBootDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

//@Component - To automatically detect our custom beans
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Email already registered");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(String email) {
        Optional<Student> s = studentRepository.findStudentByEmail(email);
        if(!(s.isPresent()))
        {
            throw new IllegalStateException("Email not present");
        }
        studentRepository.deleteById(s.get().getId());
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->new IllegalStateException("Student not found"));

        if (name != null && name.length()>0 && !Objects.equals(name,student.getName()))
        {
            student.setName(name);
        }

        if(email !=null && email.length()>0 && !Objects.equals(email,student.getEmail()))
        {
            Optional<Student> s = studentRepository.findStudentByEmail(email);
            if(s.isPresent())
            {
                throw new IllegalStateException("Email already exists");
            }
            student.setEmail(email);
        }

    }
}
