package com.rozetka.service.impl;

import com.rozetka.dto.request.ProductDTO;
import com.rozetka.dto.response.ProductResponseDTO;
import com.rozetka.entity.Product;
import com.rozetka.entity.ProductCategory;
import com.rozetka.entity.QProduct;
import com.rozetka.repository.ProductCategoryRepository;
import com.rozetka.repository.ProductRepository;
import com.rozetka.repository.querydsl.QPredicates;
import com.rozetka.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper mapper;


    @Override
    public Page<ProductResponseDTO> findAllByPredicate(String categoryName,
                                                       String productName,
                                                       Pageable pageable) {

        var predicate = QPredicates.builder()
                .add(categoryName, QProduct.product.category.categoryName::containsIgnoreCase)
                .add(productName, QProduct.product.name::containsIgnoreCase)
                .build();

        return productRepository.findAll(predicate, pageable)
                .map(entity -> mapper.map(entity, ProductResponseDTO.class));
    }

    @Override
    public Optional<ProductResponseDTO> findById(Long id) {

        return productRepository.findById(id)
                .map(entity -> mapper.map(entity, ProductResponseDTO.class));
    }

    @Override
    public Page<ProductResponseDTO> findAllByCategoryId(Long id, Pageable pageable) {

        return productRepository.findAllByCategoryId(id, pageable)
                .map(entity -> mapper.map(entity, ProductResponseDTO.class));
    }

    @Override
    @Transactional
    public ProductResponseDTO create(ProductDTO productDTO) {

        ProductCategory productCategory = productCategoryRepository.findById(productDTO.getProductCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Product product = new Product();
        product.setCategory(productCategory);
        product.setName(productDTO.getName());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());

        Product productEntity = productRepository.save(product);

        return mapper.map(productEntity, ProductResponseDTO.class);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return productRepository.findById(id)
                .map(entity -> {
                    productRepository.delete(entity);
                    productRepository.flush();
                    return true;
                }).orElse(false);
    }
}
