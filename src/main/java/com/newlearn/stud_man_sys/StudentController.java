package com.newlearn.stud_man_sys;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final Student_DAO studentDao;

    public StudentController(Student_DAO studentDao) {
        this.studentDao = studentDao;
    }

    // Test the API
    @GetMapping("/test")
    public String test() {
        return "Student Management API is working!";
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentDao.findAll());
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return studentDao.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                null,
                request.getName(),
                request.getAge(),
                request.getCourse(),
                request.getMarks()
        );

        Student savedStudent = studentDao.save(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStudent);
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean deleted = studentDao.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
