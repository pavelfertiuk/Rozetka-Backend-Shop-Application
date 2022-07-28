package com.rozetka.service.impl;

import com.rozetka.dto.request.AddressDTO;
import com.rozetka.dto.response.AddressResponseDTO;
import com.rozetka.entity.Address;
import com.rozetka.repository.AddressRepository;
import com.rozetka.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<AddressResponseDTO> findAll() {

        return addressRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, AddressResponseDTO.class))
                .toList();
    }

    @Override
    public Optional<AddressResponseDTO> findById(Long id) {

        return addressRepository.findById(id)
                .map(entity -> modelMapper.map(entity, AddressResponseDTO.class));
    }

    @Override
    @Transactional
    public AddressResponseDTO create(AddressDTO addressDTO) {

        Address address = modelMapper.map(addressDTO, Address.class);

        Address addressEntity = addressRepository.save(address);

        return modelMapper.map(addressEntity, AddressResponseDTO.class);
    }
}
