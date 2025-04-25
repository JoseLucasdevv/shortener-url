package com.example.shortneer.http.request;

import org.hibernate.validator.constraints.URL;

public record UrlRequest(@URL(message = "url is not valid") String url){}