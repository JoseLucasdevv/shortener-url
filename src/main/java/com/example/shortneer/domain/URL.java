package com.example.shortneer.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uid;
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;


    public URL(){}

    public URL(Long id, UUID uid, String shortUrl, String originalUrl, LocalDateTime expiresAt,LocalDateTime createdAt){
        this.id = id;
        this.uid = uid;
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    @PrePersist
    private void prePersist(){
        this.uid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public UUID getUid(){
        return this.uid;
    }

    public String getShortUrl(){
        return this.shortUrl;
    }
    public void setShortUrl(String shortUrl){
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl(){
        return this.originalUrl;
    }
    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getExpiresAt(){
        return this.expiresAt;
    }
    public void setExpiresAt(LocalDateTime expiresAt){
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
}
