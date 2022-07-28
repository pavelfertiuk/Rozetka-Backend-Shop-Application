package com.rozetka.service.impl;

import com.rozetka.dto.request.CustomerDTO;
import com.rozetka.dto.response.CustomerResponseDTO;
import com.rozetka.entity.Customer;
import com.rozetka.repository.CustomerRepository;
import com.rozetka.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Override
    public List<CustomerResponseDTO> findAll() {

        return customerRepository.findAll().stream()
                .map(entity -> mapper.map(entity, CustomerResponseDTO.class))
                .toList();
    }

    @Override
    public Optional<CustomerResponseDTO> findById(Long id) {

        return customerRepository.findById(id)
                .map(entity -> mapper.map(entity, CustomerResponseDTO.class));
    }

    @Override
    @Transactional
    public CustomerResponseDTO create(CustomerDTO customerDTO) {

        Customer customer = mapper.map(customerDTO, Customer.class);

        Customer customerEntity = customerRepository.save(customer);

        return mapper.map(customerEntity, CustomerResponseDTO.class);
    }
}
