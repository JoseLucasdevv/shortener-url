package com.example.shortneer.http.api;

import com.example.shortneer.http.request.UrlRequest;
import com.example.shortneer.service.URLService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.application.name}")
    String baseUrl;

    @PostMapping("shortener")
    public ResponseEntity<Object> create(@RequestBody @Valid UrlRequest url){
        this.urlService.TestPrintResult();
        System.out.println(baseUrl);


        return ResponseEntity.ok().build();

    }



    @GetMapping("shortener")
    public ModelAndView redirect(@RequestParam() String shortUrl, HttpServletResponse httpServletResponse){



        return new ModelAndView("redirect:" + httpServletResponse.encodeRedirectURL("https://google.com"));
    }

}



