package com.soft.mailinglist.command;

import lombok.Data;

@Data
public class RequestCommand {
    private String email;
    private String text;
}
