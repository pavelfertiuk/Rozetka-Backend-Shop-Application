package com.rozetka.service;

import com.rozetka.dto.request.ProductCategoryDTO;
import com.rozetka.dto.response.ProductCategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    List<ProductCategoryResponseDTO> findAll();

    Optional<ProductCategoryResponseDTO> findById(Long id);

    ProductCategoryResponseDTO create(ProductCategoryDTO productCategoryDTO);

    Optional<ProductCategoryResponseDTO> update(Long id, ProductCategoryDTO productCategoryDTO);

    boolean delete(Long id);
}
