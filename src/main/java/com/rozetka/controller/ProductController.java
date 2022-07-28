package com.rozetka.controller;

import com.rozetka.dto.request.ProductDTO;
import com.rozetka.dto.response.ProductResponseDTO;
import com.rozetka.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public Page<ProductResponseDTO> findAllByPredicate(@RequestParam(required = false) String categoryName,
                                                       @RequestParam(required = false) String productName,
                                                       Pageable pageable) {

        return productService.findAllByPredicate(categoryName, productName, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ProductResponseDTO findById(@PathVariable("id") Long id) {

        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/categoryId/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public Page<ProductResponseDTO> findAllByCategoryId(@PathVariable("id") Long id, Pageable pageable) {

        return productService.findAllByCategoryId(id, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductResponseDTO create(@Valid @RequestBody ProductDTO productDTO) {

        return productService.create(productDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return productService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
