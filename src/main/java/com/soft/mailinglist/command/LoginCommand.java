package com.soft.mailinglist.command;

import lombok.Data;

@Data
public class LoginCommand {
    private String username;
    private String password;


    private LoginCommand() {}

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
