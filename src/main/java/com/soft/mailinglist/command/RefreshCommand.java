package com.soft.mailinglist.command;

import lombok.Data;

@Data
public class RefreshCommand {
    private String refreshToken;

    public RefreshCommand(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public RefreshCommand() {}
}
