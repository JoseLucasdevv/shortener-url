package com.example.shortneer.http.api;

import com.example.shortneer.exceptions.ParameterException;
import com.example.shortneer.http.request.UrlRequest;
import com.example.shortneer.http.response.UrlResponse;
import com.example.shortneer.service.URLService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.net.URI;

import java.util.Optional;

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

    @PostMapping("shortener")
    public ResponseEntity<UrlResponse> create(@RequestBody @Valid UrlRequest urlRequest){
        UrlResponse response = this.urlService.save(urlRequest.url());
        URI location = URI.create("http://localhost");
        return ResponseEntity.created(location).body(response);

    }


    @GetMapping("shortener/{shortUrl}")
    public ModelAndView redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse){

            String response = this.urlService.verifyShortenerUrl(shortUrl);

        return new ModelAndView("redirect:" + httpServletResponse.encodeRedirectURL(response));
    }

}



