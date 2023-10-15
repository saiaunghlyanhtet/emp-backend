package com.sahh.userscrud.appusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahh.userscrud.dto.AppUserDTO;
import com.sahh.userscrud.dto.DTOMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private DTOMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create a new user
    public void createUser(AppUsers appUser) {
        AppUsers newUser = new AppUsers();
        newUser.setFirstname(appUser.getFirstname());
        newUser.setLastname(appUser.getLastname());
        newUser.setEmail(appUser.getEmail());
        newUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        newUser.setAppUserRole(AppUserRole.USER);
        newUser.setDelFlag(0);
        userRepository.save(newUser);
    }

    // Update an existing user
    public void updateUser(Long userId, AppUserDTO userDTO) {
        Optional<AppUsers> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            AppUsers existingUser = optionalUser.get();
            // Update user properties from the DTO
            existingUser.setFirstname(userDTO.getFirstname());
            existingUser.setLastname(userDTO.getLastname());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setAppUserRole(existingUser.getAppUserRole());
            userRepository.save(existingUser);

        } else {
            throw new UsernameNotFoundException("User with ID " + userId + " not found.");
        }
    }

    // Get user by ID
    public AppUserDTO getUserById(Long userId) {
        Optional<AppUsers> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            AppUsers user = optionalUser.get();
            return userMapper.toDto(user);
        } else {
            throw new UsernameNotFoundException("User with ID " + userId + " not found.");
        }
    }

    // Get all users
    public List<AppUserDTO> getAllUsers() {
        List<AppUsers> allUsers = userRepository.findAll(); // Assuming userRepository is your JPA repository

        List<AppUserDTO> filteredUsers = allUsers.stream()
                .filter(user -> user.getDelFlag() == 0)
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return filteredUsers;
    }

    // Delete user by ID
    public void deleteUserById(Long userId) {
        if (userRepository.existsById(userId)) {
            Optional<AppUsers> existingUser = userRepository.findById(userId);
            if (existingUser.isPresent()) {
                AppUsers user = existingUser.get();
                user.setDelFlag(1);
                userRepository.save(user);
            }
        } else {
            throw new UsernameNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
