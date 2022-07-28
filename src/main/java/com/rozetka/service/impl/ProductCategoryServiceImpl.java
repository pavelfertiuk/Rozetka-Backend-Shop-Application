package com.rozetka.service.impl;

import com.rozetka.dto.request.ProductCategoryDTO;
import com.rozetka.dto.response.ProductCategoryResponseDTO;
import com.rozetka.entity.ProductCategory;
import com.rozetka.repository.ProductCategoryRepository;
import com.rozetka.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductCategoryResponseDTO> findAll() {

        return productCategoryRepository.findAll().stream()
                .map(entity -> mapper.map(entity, ProductCategoryResponseDTO.class))
                .toList();
    }

    @Override
    public Optional<ProductCategoryResponseDTO> findById(Long id) {

        return productCategoryRepository.findById(id)
                .map(entity -> mapper.map(entity, ProductCategoryResponseDTO.class));
    }

    @Override
    @Transactional
    public ProductCategoryResponseDTO create(ProductCategoryDTO productCategoryDTO) {

        ProductCategory productCategory = mapper.map(productCategoryDTO, ProductCategory.class);

        ProductCategory entityProductCategory = productCategoryRepository.save(productCategory);

        return mapper.map(entityProductCategory, ProductCategoryResponseDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProductCategoryResponseDTO> update(Long id, ProductCategoryDTO productCategoryDTO) {

        return productCategoryRepository.findById(id)
                .map(entity -> {
                    entity.setCategoryName(productCategoryDTO.getCategoryName());
                    productCategoryRepository.saveAndFlush(entity);
                    return mapper.map(entity, ProductCategoryResponseDTO.class);
                });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return productCategoryRepository.findById(id)
                .map(entity -> {
                    productCategoryRepository.delete(entity);
                    productCategoryRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
