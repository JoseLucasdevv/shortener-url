package com.example.shortneer.http.api;

import com.example.shortneer.http.request.UrlRequest;
import com.example.shortneer.http.response.UrlResponse;
import com.example.shortneer.service.URLService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.net.URI;


import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/api/v1")
public class ShortenerUriResource {

private final URLService urlService;

public ShortenerUriResource(URLService urlService){
    this.urlService = urlService;
}

    @PostMapping(value = "shortener",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<EntityModel<UrlResponse>> create(@RequestBody @Valid UrlRequest urlRequest){
        EntityModel<UrlResponse> response = this.urlService.save(urlRequest.url());
        URI location = URI.create("http://localhost");
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping("shortener/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse){

            String response = this.urlService.verifyShortenerUrl(shortUrl);
            String urlEncoded = httpServletResponse.encodeRedirectURL(response);
            try{
                httpServletResponse.sendRedirect(urlEncoded);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return ResponseEntity.status(302).build();
    }

}



