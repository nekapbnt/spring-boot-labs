package ru.nabiev.SpringBootWebSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nabiev.SpringBootWebSecurity.dto.UserDto;
import ru.nabiev.SpringBootWebSecurity.entity.*;
import ru.nabiev.SpringBootWebSecurity.repository.PrivilegeRepository;
import ru.nabiev.SpringBootWebSecurity.repository.RoleRepository;
import ru.nabiev.SpringBootWebSecurity.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Privilege readPrivilege = createPrivilegeIfNotFound("PRIVILEGE_READ");
        Privilege writePrivilege = createPrivilegeIfNotFound("PRIVILEGE_WRITE");
        Privilege updatePrivilege = createPrivilegeIfNotFound("PRIVILEGE_UPDATE");
        Privilege moderatePrivilege = createPrivilegeIfNotFound("PRIVILEGE_MODERATE");
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, updatePrivilege, moderatePrivilege);
        List<Privilege> userPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, updatePrivilege);
        List<Privilege> readOnlyPrivileges = Arrays.asList(readPrivilege);

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);
        Role readOnlyRole = createRoleIfNotFound("ROLE_READONLY", readOnlyPrivileges);
        if (user.getEmail().equals("admin@admin")) {
            user.setRoles(Arrays.asList(adminRole));
        }
        else {
            user.setRoles(Arrays.asList(readOnlyRole));
        }
        /*user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));*/
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles().get(0).getName());
        return userDto;
    }

    private Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role createRoleIfNotFound(String name, List<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    private void changeRole(User user, Role role) {
        user.setRoles(Arrays.asList(role));
    }

    /*private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }*/

}