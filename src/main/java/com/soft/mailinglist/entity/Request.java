package com.soft.mailinglist.entity;

import com.soft.mailinglist.enums.RequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Request {

    @Id
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "text")
    private String text;
    @Column(name = "status")
    private RequestStatus status;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "doneAt")
    private LocalDateTime doneAt;

    public Request(String email,String text) {
        this.email = email;
        this.text = text;
        this.status = RequestStatus.NOT_COMPLETED;
        this.createdAt = LocalDateTime.now();
        this.doneAt = null;
    }

    public Request() {
    }
}
