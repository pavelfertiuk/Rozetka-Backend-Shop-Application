package com.rozetka.service;

import com.rozetka.dto.request.AddressDTO;
import com.rozetka.dto.response.AddressResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<AddressResponseDTO> findAll();

    Optional<AddressResponseDTO> findById(Long id);

    AddressResponseDTO create(AddressDTO addressDTO);
}
