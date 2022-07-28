package com.rozetka.service;

import com.rozetka.dto.request.CountryDTO;
import com.rozetka.dto.response.CountryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<CountryResponseDTO> findAll();

    Optional <CountryResponseDTO> findById(Integer id);

    CountryResponseDTO create(CountryDTO countryDTO);

    Optional<CountryResponseDTO> update(Integer id, CountryDTO countryDTO);

    boolean delete(Integer id);
}
