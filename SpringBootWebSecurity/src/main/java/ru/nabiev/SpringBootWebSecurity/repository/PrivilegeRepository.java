package ru.nabiev.SpringBootWebSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabiev.SpringBootWebSecurity.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

}
