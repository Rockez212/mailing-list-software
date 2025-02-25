package com.soft.mailinglist.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterCommand {
    @NotBlank(message = "Username must not be empty")
    private String username;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8,max = 8, message = "Password must be exactly 8 characters long")
    private String email;
    @NotBlank(message = "email must not be empty")
    @Email(message = "Incorrect email")
    private String password;


    public RegisterCommand(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private RegisterCommand() {}

}
