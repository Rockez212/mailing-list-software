package com.soft.mailinglist.command;

import lombok.Data;

@Data
public class RegisterCommand {
    private String username;
    private String email;
    private String password;


    private RegisterCommand() {}

    public RegisterCommand(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
