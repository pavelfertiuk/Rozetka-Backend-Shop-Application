package com.rozetka.integration.service;

import com.rozetka.dto.request.ProductCategoryDTO;
import com.rozetka.dto.response.ProductCategoryResponseDTO;
import com.rozetka.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductCategoryServiceIT {

    public static final Long PRODUCT_CATEGORY_ID = 1L;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    void findAll() {

        List<ProductCategoryResponseDTO> result = productCategoryService.findAll();

        assertThat(result).hasSize(6);
    }

    @Test
    void findById() {

        Optional<ProductCategoryResponseDTO> optionalCategory =
                productCategoryService.findById(PRODUCT_CATEGORY_ID);

        assertTrue(optionalCategory.isPresent());

        optionalCategory.ifPresent(category -> assertEquals("Мобільні телефони", category.getCategoryName()));
    }

    @Test
    void create() {

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO("categoryName");

        ProductCategoryResponseDTO productCategory = productCategoryService.create(productCategoryDTO);

        assertEquals(productCategoryDTO.getCategoryName(), productCategory.getCategoryName());
    }

    @Test
    void update() {

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO("categoryNameAfterUpdate");

        Optional<ProductCategoryResponseDTO> updatedCategory =
                productCategoryService.update(9L, productCategoryDTO);

        assertTrue(updatedCategory.isPresent());

        updatedCategory.ifPresent(category ->
                assertEquals(productCategoryDTO.getCategoryName(), category.getCategoryName()));
    }

    @Test
    void delete() {

        assertFalse(productCategoryService.delete(-100L));

        assertTrue(productCategoryService.delete(9L));
    }
}
