package com.soft.mailinglist.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterCommand {
    @NotBlank(message = "Username must not be empty")
    private String username;
    @NotBlank(message = "email must not be empty")
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8,max = 8, message = "Password must be exactly 8 characters long")
    private String password;
}
