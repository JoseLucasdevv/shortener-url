package com.example.shortneer.http.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public record UrlResponse(String shortUrl, String OriginalUrl, String uid, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime expiresAt, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime createdAt)  {



}
