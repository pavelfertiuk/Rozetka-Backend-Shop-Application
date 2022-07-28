package com.rozetka.dto.response;

import com.rozetka.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponseDTO {

    private Integer id;
    private String name;
    private Country country;
}
