package com.krish.StudentLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krish.StudentLogin.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(String username);
    Student findByEmail(String email);
}