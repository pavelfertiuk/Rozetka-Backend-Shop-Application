package com.rozetka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull
    @NotBlank
    private String street;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String country;
}
