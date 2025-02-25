package com.soft.mailinglist.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginCommand {
    @NotBlank(message = "Username must not be empty")
    private String username;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8,max = 8, message = "Password must be exactly 8 characters long")
    private String password;


    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private LoginCommand() {}
}
