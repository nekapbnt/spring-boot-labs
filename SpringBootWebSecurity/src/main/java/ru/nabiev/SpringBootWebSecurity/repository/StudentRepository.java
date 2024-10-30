package ru.nabiev.SpringBootWebSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabiev.SpringBootWebSecurity.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}