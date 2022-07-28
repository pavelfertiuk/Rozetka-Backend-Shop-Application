package com.rozetka.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

    @NotBlank
    @NotEmpty
    private String name;

    @NonNull
    private Integer countryId;
}
