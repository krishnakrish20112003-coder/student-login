package com.krish.StudentLogin.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.krish.StudentLogin.model.Student;
import com.krish.StudentLogin.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // 🔹 REGISTER
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {

        if (repo.findByUsername(username) != null) {
            return "Username already exists";
        }

        if (repo.findByEmail(email) != null) {
            return "Email already exists";
        }

        Student s = new Student();
        s.setUsername(username);
        s.setPassword(password);
        s.setEmail(email);
        s.setRegistrationDate(LocalDate.now());

        repo.save(s);

        return "Student Registered Successfully";
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        Student s = repo.findByUsername(username);

        if (s == null) {
            return "Student not found";
        }

        if (!s.getPassword().equals(password)) {
            return "Incorrect password";
        }

        return "Login Successful";
    }
}