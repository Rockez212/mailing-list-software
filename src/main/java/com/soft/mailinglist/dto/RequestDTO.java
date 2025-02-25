package com.soft.mailinglist.dto;

import com.soft.mailinglist.enums.RequestStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDTO {
    private String toEmail;
    private String text;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime doneAt;

    public RequestDTO(String toEmail, String text, RequestStatus status, LocalDateTime createdAt, LocalDateTime doneAt) {
        this.toEmail = toEmail;
        this.text = text;
        this.status = status;
        this.createdAt = createdAt;
        this.doneAt = doneAt;
    }
    private RequestDTO() {}
}
