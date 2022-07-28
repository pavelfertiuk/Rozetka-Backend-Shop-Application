package com.rozetka.service.impl;

import com.rozetka.dto.request.RegionDTO;
import com.rozetka.dto.response.RegionResponseDTO;
import com.rozetka.entity.Country;
import com.rozetka.entity.Region;
import com.rozetka.repository.CountryRepository;
import com.rozetka.repository.RegionRepository;
import com.rozetka.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Override
    public List<RegionResponseDTO> finAll() {

        return regionRepository.findAll().stream()
                .map(entity -> mapper.map(entity, RegionResponseDTO.class))
                .toList();
    }

    @Override
    public List<RegionResponseDTO> findAllByCountryId(Integer countryId) {

        return regionRepository.findAllByCountryId(countryId).stream()
                .map(entity -> mapper.map(entity, RegionResponseDTO.class))
                .toList();
    }

    @Override
    public RegionResponseDTO findByIdAndCountryId(Integer id, Integer countryId) {

        return regionRepository.findByIdAndCountryId(id, countryId)
                .map(entity -> mapper.map(entity, RegionResponseDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public RegionResponseDTO create(RegionDTO regionDTO) {

        Country country = countryRepository.findById(regionDTO.getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Region region = new Region();

        region.setCountry(country);
        region.setName(regionDTO.getName());

        Region entityRegion = regionRepository.save(region);

        return mapper.map(entityRegion, RegionResponseDTO.class);
    }

    @Override
    @Transactional
    public Optional<RegionResponseDTO> update(Integer id, RegionDTO regionDTO) {

        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Country country = countryRepository.findById(regionDTO.getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        region.setCountry(country);
        region.setName(regionDTO.getName());

        Region updatedRegion = regionRepository.save(region);

        return Optional.of(mapper.map(updatedRegion, RegionResponseDTO.class));
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return regionRepository.findById(id)
                .map(entity -> {
                    regionRepository.delete(entity);
                    regionRepository.flush();
                    return true;
                }).orElse(false);
    }
}

