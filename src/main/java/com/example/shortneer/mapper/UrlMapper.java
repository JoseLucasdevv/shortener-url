package com.example.shortneer.mapper;

import com.example.shortneer.domain.URL;
import com.example.shortneer.http.response.UrlResponse;

public class UrlMapper {

    private UrlMapper(){}

    public static UrlResponse urlToUrlResponse(URL url){


        return new UrlResponse(url.getShortUrl(), url.getOriginalUrl(), url.getUid().toString(), url.getExpiresAt(), url.getCreatedAt());
    }
    
}
