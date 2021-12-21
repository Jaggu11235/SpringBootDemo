package com.example.SpringBootDemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.MAY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student jagadeesh = new Student(
                    "Jagadeesh",
                    "jagadeesh.renuguntla@gmail.com",
                    LocalDate.of(1998, MAY,18));
            Student ram = new Student(
                    "Ram",
                    "ram.renuguntla@gmail.com",
                    LocalDate.of(1994, APRIL,18));
            repository.saveAll(
                    Arrays.asList(jagadeesh, ram));
        };
    }
}
