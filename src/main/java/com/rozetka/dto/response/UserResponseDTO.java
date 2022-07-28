package com.rozetka.dto.response;

import com.rozetka.entity.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<AppUserRole> appUserRoles;
}
