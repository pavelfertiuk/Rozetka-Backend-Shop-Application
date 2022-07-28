package com.rozetka.service.impl;

import com.rozetka.dto.request.CountryDTO;
import com.rozetka.dto.response.CountryResponseDTO;
import com.rozetka.entity.Country;
import com.rozetka.repository.CountryRepository;
import com.rozetka.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Override
    public List<CountryResponseDTO> findAll() {

        return countryRepository.findAll().stream()
                .map(entity -> mapper.map(entity, CountryResponseDTO.class))
                .toList();
    }

    @Override
    public Optional<CountryResponseDTO> findById(Integer id) {

        return countryRepository.findById(id)
                .map(entity -> mapper.map(entity, CountryResponseDTO.class));
    }

    @Override
    @Transactional
    public CountryResponseDTO create(CountryDTO countryDTO) {

        Country country = mapper.map(countryDTO, Country.class);

        Country entityCountry = countryRepository.save(country);

        return mapper.map(entityCountry, CountryResponseDTO.class);
    }

    @Override
    @Transactional
    public Optional<CountryResponseDTO> update(Integer id, CountryDTO countryDTO) {

        Optional<Country> optionalCountry = countryRepository.findById(id);

        Country country = optionalCountry.get();

        country.setCode(countryDTO.getCode());
        country.setName(countryDTO.getName());

        Country entityCountry = countryRepository.saveAndFlush(country);

        return Optional.of(mapper.map(entityCountry, CountryResponseDTO.class));
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {

        return countryRepository.findById(id)
                .map(entity -> {
                    countryRepository.delete(entity);
                    countryRepository.flush();
                    return true;
                }).orElse(false);
    }
}
