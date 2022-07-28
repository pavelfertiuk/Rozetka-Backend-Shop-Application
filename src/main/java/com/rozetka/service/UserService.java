package com.rozetka.service;

import com.rozetka.dto.response.UserResponseDTO;
import com.rozetka.entity.AppUser;

import java.util.Optional;

public interface UserService {
    String signIn(String username, String password);
    String signUp(AppUser appUser);
    Optional<UserResponseDTO> findById(Integer id);
    boolean delete(Integer id);
}
