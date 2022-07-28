package com.rozetka.service;

import com.rozetka.dto.request.RegionDTO;
import com.rozetka.dto.response.RegionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RegionService {
    List<RegionResponseDTO> finAll();
    List<RegionResponseDTO> findAllByCountryId(Integer countryId);
    RegionResponseDTO findByIdAndCountryId(Integer id, Integer countryId);
    RegionResponseDTO create(RegionDTO regionDTO);
    Optional<RegionResponseDTO> update(Integer id, RegionDTO regionDTO);
    boolean delete(Integer id);
}
