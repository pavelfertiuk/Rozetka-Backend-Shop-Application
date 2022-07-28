package com.rozetka.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String category;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
}
