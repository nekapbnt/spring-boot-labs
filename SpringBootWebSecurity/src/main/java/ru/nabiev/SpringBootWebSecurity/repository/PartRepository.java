package ru.nabiev.SpringBootWebSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabiev.SpringBootWebSecurity.entity.Part;

public interface PartRepository extends JpaRepository<Part, Long> {

}
