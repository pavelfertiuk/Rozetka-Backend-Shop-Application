package com.rozetka.service;

import com.rozetka.dto.request.ProductDTO;
import com.rozetka.dto.response.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<ProductResponseDTO> findAllByPredicate(String categoryName, String productName, Pageable pageable);
    Optional<ProductResponseDTO> findById(Long id);
    Page<ProductResponseDTO> findAllByCategoryId(Long id, Pageable pageable);
    ProductResponseDTO create(ProductDTO productDTO);
    boolean delete(Long id);
}
