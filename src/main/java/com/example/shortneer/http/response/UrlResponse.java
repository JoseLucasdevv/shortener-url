package com.example.shortneer.http.response;

import java.time.LocalDateTime;

public record UrlResponse(String shortUrl, String OriginalUrl,String uid,LocalDateTime expiresAt,LocalDateTime createdAt) {}
