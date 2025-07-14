package com.soft.mailinglist.entity;

import com.soft.mailinglist.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "requests")
@Getter
@Setter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    @SequenceGenerator(name = "request_seq", sequenceName = "request_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "to_email")
    private String toEmail;
    @Column(name = "text")
    private String text;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "done_at")
    private LocalDateTime doneAt;

    public Request(String toEmail, String text) {
        this.toEmail = toEmail;
        this.text = text;
        this.status = RequestStatus.NOT_COMPLETED;
        this.createdAt = LocalDateTime.now();
        this.doneAt = null;
    }

    protected Request() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
