package com.rozetka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 2)
    private String code;
    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 25)
    private String name;
}
