package com.example.studentmanagementbackend.Controller;

import com.example.studentmanagementbackend.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {
    List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1,"Priyansh","Surat"),
                    new Student(2,"Priyank","Rajkot"),
                    new Student(3,"Deep","Surat"),
                    new Student(4,"Jeel","Surat")
            )
    );

    @GetMapping("/listStudents")
    public List<Student> getAllStudents(){
        return students;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getId()==id){
                return students.get(i);
            }
        }
        return null;
    }

    @PostMapping("/student")
    public List<Student> addStudent(@RequestBody Student student){
        students.add(student);
        return students;
    }

    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudent(@PathVariable Integer id){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getId()==id){
                students.remove(i);
            }
        }
        return students;
    }

    @PutMapping("/student/{id}")
    public List<Student> updateStudent(@RequestBody Student student,@PathVariable Integer id){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getId()==id){
                students.get(i).setName(student.getName());
                students.get(i).setAddress(student.getAddress());
            }
        }
        return students;
    }
}
