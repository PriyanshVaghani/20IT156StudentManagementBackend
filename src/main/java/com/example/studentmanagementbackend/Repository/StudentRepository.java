package com.example.studentmanagementbackend.Repository;

import com.example.studentmanagementbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
