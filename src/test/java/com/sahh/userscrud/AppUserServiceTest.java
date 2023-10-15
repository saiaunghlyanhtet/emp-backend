package com.sahh.userscrud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sahh.userscrud.appusers.AppUserRepository;
import com.sahh.userscrud.appusers.AppUserService;
import com.sahh.userscrud.appusers.AppUsers;
import com.sahh.userscrud.dto.AppUserDTO;
import com.sahh.userscrud.dto.DTOMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private DTOMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void testCreateUser() {
        AppUsers appUser = new AppUsers();
        appUser.setFirstname("John");
        appUser.setLastname("Doe");
        appUser.setEmail("john.doe@example.com");
        appUser.setPassword("password");

        when(passwordEncoder.encode(appUser.getPassword())).thenReturn("encodedPassword");

        appUserService.createUser(appUser);

        verify(userRepository, times(1)).save(any(AppUsers.class));
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        AppUsers existingUser = new AppUsers();
        existingUser.setId(userId);
        existingUser.setFirstname("John");
        existingUser.setLastname("Doe");
        existingUser.setEmail("john.doe@example.com");

        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setFirstname("Jane");
        userDTO.setLastname("Doe");
        userDTO.setEmail("jane.doe@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        appUserService.updateUser(userId, userDTO);

        verify(userRepository, times(1)).save(any(AppUsers.class));
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        AppUsers existingUser = new AppUsers();
        existingUser.setId(userId);
        existingUser.setFirstname("John");
        existingUser.setLastname("Doe");
        existingUser.setEmail("john.doe@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        appUserService.getUserById(userId);

        verify(userMapper, times(1)).toDto(any(AppUsers.class));
    }

    @Test
    public void testGetAllUsers() {
        AppUsers user1 = new AppUsers();
        user1.setId(1L);
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setEmail("john.doe@example.com");

        AppUsers user2 = new AppUsers();
        user2.setId(2L);
        user2.setFirstname("Jane");
        user2.setLastname("Doe");
        user2.setEmail("jane.doe@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        appUserService.getAllUsers();

        verify(userMapper, times(2)).toDto(any(AppUsers.class));
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        AppUsers existingUser = new AppUsers();
        existingUser.setId(userId);

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        appUserService.deleteUserById(userId);

        verify(userRepository, times(1)).save(any(AppUsers.class));
    }

}
