package com.example.shortneer.service;

import java.time.LocalDateTime;

import com.example.shortneer.http.api.ShortenerUriResource;
import com.example.shortneer.http.request.UrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;

import com.example.shortneer.domain.URL;
import com.example.shortneer.exceptions.Exception;
import com.example.shortneer.http.response.UrlResponse;
import com.example.shortneer.mapper.UrlMapper;
import com.example.shortneer.repository.URLRepository;
import util.GenerateRandomToken;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class URLService {

    @Value("${spring.env.baseUrl}")
    private String baseurl;
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public EntityModel<UrlResponse> save(String url){
        String randomCode = GenerateRandomToken.build();
        String newUrl = baseurl + "/" + randomCode;

        URL urlDomain = new URL();

        urlDomain.setShortUrl(newUrl);
        urlDomain.setOriginalUrl(url);
        urlDomain.setExpiresAt(LocalDateTime.now().plusMinutes(30));

        URL urlFromDB =  this.urlRepository.save(urlDomain);
        var response = UrlMapper.urlToUrlResponse(urlFromDB);

        EntityModel<UrlResponse> model = EntityModel.of(response);
        model.add(linkTo(methodOn(ShortenerUriResource.class).create(new UrlRequest(url))).withSelfRel().withType("POST"));
        model.add(linkTo(methodOn(ShortenerUriResource.class).redirect(urlDomain.getShortUrl().substring(urlDomain.getShortUrl().length() - 5 ,urlDomain.getShortUrl().length() ),null)).withSelfRel().withType("GET"));

        return model;
        
    }

    public String verifyShortenerUrl(String shortUrl){
        String newUrl = baseurl + "/" + shortUrl;
        URL url = this.urlRepository.findByShortUrl(newUrl).orElseThrow(() -> new Exception("we couldn't find this shortUrl",HttpStatus.NOT_FOUND));

        if(url.getExpiresAt().isBefore(LocalDateTime.now())) throw new Exception("shortUrl was expired.", HttpStatus.BAD_REQUEST);

        

        return url.getOriginalUrl();
    }


}
