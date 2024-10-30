package ru.nabiev.SpringBootWebSecurity.service;

import ru.nabiev.SpringBootWebSecurity.dto.UserDto;
import ru.nabiev.SpringBootWebSecurity.entity.User;

import java.util.List;

public interface UserService {

    void  saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
