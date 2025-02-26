package com.soft.mailinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailingListSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingListSoftwareApplication.class, args);
    }

}
