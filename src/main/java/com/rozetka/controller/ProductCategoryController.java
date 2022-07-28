package com.rozetka.controller;

import com.rozetka.dto.request.ProductCategoryDTO;
import com.rozetka.dto.response.ProductCategoryResponseDTO;
import com.rozetka.service.ProductCategoryService;
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
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<ProductCategoryResponseDTO> findAll() {

        return productCategoryService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ProductCategoryResponseDTO findById(@PathVariable("id") Long id) {

        return productCategoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductCategoryResponseDTO create(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {

        return productCategoryService.create(productCategoryDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductCategoryResponseDTO update(@PathVariable("id") Long id,
                                             @RequestBody ProductCategoryDTO productCategoryDTO) {

        return productCategoryService.update(id, productCategoryDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return productCategoryService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
