package com.soft.mailinglist.entity;

import com.soft.mailinglist.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {

    @Id
    private Long id;
    @Column(name = "email")
    private String toEmail;
    @Column(name = "text")
    private String text;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "doneAt")
    private LocalDateTime doneAt;

    public Request(String toEmail,String text) {
        this.toEmail = toEmail;
        this.text = text;
        this.status = RequestStatus.NOT_COMPLETED;
        this.createdAt = LocalDateTime.now();
        this.doneAt = null;
    }

    public Request() {
    }
}
