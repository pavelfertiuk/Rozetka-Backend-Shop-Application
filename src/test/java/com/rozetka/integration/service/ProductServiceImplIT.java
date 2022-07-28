package com.rozetka.integration.service;

import com.rozetka.dto.request.ProductDTO;
import com.rozetka.dto.response.ProductResponseDTO;
import com.rozetka.entity.ProductCategory;
import com.rozetka.repository.ProductCategoryRepository;
import com.rozetka.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceImplIT {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductCategoryRepository categoryRepository;

    public static final Long PRODUCT_ID = 113L;
    public static final Long CATEGORY_ID = 5L;
    public static final String CATEGORY_NAME = "Телефони";
    public static final String PRODUCT_NAME = "Samsung";
    public Pageable pageable;

    @Test
    void findAllByPredicate() {

        pageable = PageRequest.of(0, 10, Sort.by("id"));

        Page<ProductResponseDTO> result = productService.findAllByPredicate(CATEGORY_NAME, PRODUCT_NAME, pageable);

        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {

        Optional<ProductResponseDTO> optionalProduct = productService.findById(PRODUCT_ID);

        assertTrue(optionalProduct.isPresent());
        optionalProduct.ifPresent(product ->
                Assertions.assertEquals("testName", product.getName()));

    }

    @Test
    void findAllByCategoryId() {

        pageable = PageRequest.of(0, 20, Sort.by("id"));

        Optional<ProductCategory> optionalProductCategory = categoryRepository.findById(CATEGORY_ID);

        assertTrue(optionalProductCategory.isPresent());

        Page<ProductResponseDTO> result = productService.findAllByCategoryId(CATEGORY_ID, pageable);

        assertThat(result).hasSize(20);
    }

    @Test
    void create() {

        Optional<ProductCategory> optionalProductCategory = categoryRepository.findById(CATEGORY_ID);

        assertTrue(optionalProductCategory.isPresent());

        ProductDTO productDTO = new ProductDTO("testName", "testDesc", new BigDecimal("10000.00"), "123,321", CATEGORY_ID);

        ProductResponseDTO actualProduct = productService.create(productDTO);

        assertEquals(productDTO.getDescription(), actualProduct.getDescription());
        assertEquals(productDTO.getName(), actualProduct.getName());
        assertEquals(productDTO.getUnitPrice(), actualProduct.getUnitPrice());
        assertEquals(productDTO.getImageUrl(), actualProduct.getImageUrl());
    }

    @Test
    void delete() {

        assertFalse(productService.delete(-500L));

        assertTrue(productService.delete(PRODUCT_ID));
    }
}
