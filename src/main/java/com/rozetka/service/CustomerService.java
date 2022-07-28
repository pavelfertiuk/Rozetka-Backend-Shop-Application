package com.rozetka.service;

import com.rozetka.dto.request.CustomerDTO;
import com.rozetka.dto.response.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();

    Optional<CustomerResponseDTO> findById(Long id);

    CustomerResponseDTO create(CustomerDTO customerDTO);
}
