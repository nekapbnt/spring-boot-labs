package ru.nabiev.SpringBootWebSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabiev.SpringBootWebSecurity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}