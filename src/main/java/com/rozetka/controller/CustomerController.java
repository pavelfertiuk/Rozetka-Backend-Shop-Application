package com.rozetka.controller;

import com.rozetka.dto.request.CustomerDTO;
import com.rozetka.dto.response.CustomerResponseDTO;
import com.rozetka.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomerResponseDTO> findAll() {

        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CustomerResponseDTO findById(@PathVariable("id") Long id) {

        return customerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public CustomerResponseDTO create(@Valid @RequestBody CustomerDTO customerDTO) {

        return customerService.create(customerDTO);
    }
}
