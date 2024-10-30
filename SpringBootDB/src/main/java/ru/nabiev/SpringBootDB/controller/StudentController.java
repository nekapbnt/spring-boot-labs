package ru.nabiev.SpringBootDB.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabiev.SpringBootDB.entity.Student;
import ru.nabiev.SpringBootDB.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> allStudents() {
        try {
            List<Student> allStudents = studentService.getAllStudents();
            if (allStudents.isEmpty()) {
                log.info("Not found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Success: {}", allStudents);
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        try {
            Student student = studentService.getStudent(id);
            if (student == null) {
                log.info("{} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            log.info("Success id {}: {}", id, student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            if (savedStudent == null) {
                log.error("Not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.info("Success id {}", savedStudent.getId());
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        try {
            Student updatedStudent = studentService.saveStudent(student);
            if (updatedStudent == null) {
                log.error("Not found");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            log.info("Success id {}", updatedStudent.getId());
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
            log.info("Success id {}.", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
