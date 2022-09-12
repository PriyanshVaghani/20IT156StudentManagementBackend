package com.example.studentmanagementbackend.Controller;

import com.example.studentmanagementbackend.Model.Student;
import com.example.studentmanagementbackend.Payload.LoginRequest;
import com.example.studentmanagementbackend.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String displayWelcomeMessage() {
        return "<center><h1>Welcome to the Spring Boot Security!!!</h1></center>";
    }

    @GetMapping("/listStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    }

    @PostMapping("/student")
    public String addStudent(@RequestBody Student student) {
        boolean studentExists = studentRepository.existsById(student.getId());
        if (!studentExists) {
            studentRepository.save(student);
            return "Record is added successfully";
        } else {
            return "Student already Exists!!!";
        }
    }

    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudent(@PathVariable Integer id) {
        studentRepository.delete(studentRepository.findById(id).get());
        return studentRepository.findAll();
    }

    @PutMapping("/student/{id}")
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        Student studentObj = studentRepository.findById(id).get();
        studentObj.setName(student.getName());
        studentObj.setAddress(student.getAddress());
        studentRepository.save(studentObj);
        return studentRepository.findAll();
    }

    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login Successful";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Logged out successful";
    }

}
