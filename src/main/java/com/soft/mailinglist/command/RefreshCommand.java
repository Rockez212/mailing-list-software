package com.soft.mailinglist.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshCommand {
    @NotBlank(message = "refresh token must not be empty")
    private String refreshToken;
}
