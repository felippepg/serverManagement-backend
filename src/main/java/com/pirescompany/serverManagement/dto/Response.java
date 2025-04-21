package com.pirescompany.serverManagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response(LocalDateTime timestamp, int statusCode, HttpStatus status, String reason, String messsage, String developerMessage, Map<?,?> data) {
}
