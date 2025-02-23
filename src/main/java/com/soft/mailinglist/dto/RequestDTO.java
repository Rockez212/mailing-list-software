package com.soft.mailinglist.dto;

import com.soft.mailinglist.enums.RequestStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDTO {
    private String email;
    private String text;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime doneAt;

    public RequestDTO(String email, String text, RequestStatus status, LocalDateTime createdAt, LocalDateTime doneAt) {
        this.email = email;
        this.text = text;
        this.status = status;
        this.createdAt = createdAt;
        this.doneAt = doneAt;
    }
    private RequestDTO() {}
}
