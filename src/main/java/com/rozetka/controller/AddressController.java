package com.rozetka.controller;

import com.rozetka.dto.request.AddressDTO;
import com.rozetka.dto.response.AddressResponseDTO;
import com.rozetka.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AddressResponseDTO> findAll() {

        return addressService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AddressResponseDTO findById(@PathVariable("id") Long id) {

        return addressService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public AddressResponseDTO create(@Valid @RequestBody AddressDTO addressDTO) {

        return addressService.create(addressDTO);
    }
}
