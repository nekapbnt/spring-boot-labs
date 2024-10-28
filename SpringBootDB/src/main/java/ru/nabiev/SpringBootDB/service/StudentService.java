package ru.nabiev.SpringBootDB.service;

import org.springframework.stereotype.Service;
import ru.nabiev.SpringBootDB.entity.Student;

import java.util.List;

@Service
public interface StudentService {
List<Student> getAllStudents();

Student saveStudent(Student student);

Student getStudent(int id);

void deleteStudent(int id);
}
