package com.example.shortneer.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import com.example.shortneer.domain.URL;
import com.example.shortneer.http.response.UrlResponse;
import com.example.shortneer.mapper.UrlMapper;
import com.example.shortneer.repository.URLRepository;
import util.GenerateRandomToken;

public class URLService {
    @Value("${spring.env.baseUrl}")
    private String baseurl;
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public UrlResponse save(String url){        
        String randomCode = GenerateRandomToken.Build();
        String newUrl = baseurl + "?token=" + randomCode;

        URL urlDomain = new URL();

        urlDomain.setShortUrl(newUrl);
        urlDomain.setOriginalUrl(url);
        urlDomain.setExpiresAt(LocalDateTime.now().plusMinutes(30));

        URL urlFromDB =  this.urlRepository.save(urlDomain);

        return UrlMapper.urlToUrlResponse(urlFromDB);

        
    }


}
