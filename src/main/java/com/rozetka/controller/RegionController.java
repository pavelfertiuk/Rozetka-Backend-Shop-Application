package com.rozetka.controller;

import com.rozetka.dto.request.RegionDTO;
import com.rozetka.dto.response.RegionResponseDTO;
import com.rozetka.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<RegionResponseDTO> findAll() {

        return regionService.finAll();
    }

    @GetMapping("/countryId")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<RegionResponseDTO> findAllByCountryId(@RequestParam("countryId") Integer countryId) {

        return regionService.findAllByCountryId(countryId);
    }

    @GetMapping("{id}/countryId")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public RegionResponseDTO findByIdAndCountryId(@PathVariable("id") Integer id,
                                                  @RequestParam("countryId") Integer countryId) {

        return regionService.findByIdAndCountryId(id, countryId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RegionResponseDTO create(@Valid @RequestBody RegionDTO regionDTO) {

        return regionService.create(regionDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RegionResponseDTO update(@PathVariable("id") Integer id,
                                    @Valid @RequestBody RegionDTO regionDTO) {

        return regionService.update(id, regionDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        return regionService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
