package com.example.shortneer.infra.config;

import com.example.shortneer.repository.URLRepository;
import com.example.shortneer.service.URLService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public URLService urlService(URLRepository urlRepository){
        return new URLService(urlRepository);
    }
}
