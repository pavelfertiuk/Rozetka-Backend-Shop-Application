package com.rozetka.controller;

import com.rozetka.dto.request.UserDTO;
import com.rozetka.dto.response.UserResponseDTO;
import com.rozetka.entity.AppUser;
import com.rozetka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signin")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        return userService.signIn(username, password);
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody UserDTO userDTO) {

        return userService.signUp(modelMapper.map(userDTO, AppUser.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO findById(@PathVariable("id") Integer id) {

        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
