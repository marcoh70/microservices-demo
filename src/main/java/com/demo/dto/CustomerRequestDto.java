package com.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDto {

    @NotEmpty(message = "UserName can not be empty")
    private String userName;
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Invalid email format")
    private String email;
    private boolean enabled;
    @NotEmpty(message = "Role can not be empty")
    private String role;
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
