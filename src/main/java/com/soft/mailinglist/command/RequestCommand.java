package com.soft.mailinglist.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCommand {
    @NotBlank(message = "email must not be empty")
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password must not be empty")
    @Size(min = 2,max = 100, message = "Text must be more than 2 characters")
    private String text;
}
