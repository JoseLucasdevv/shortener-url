package com.example.shortneer.service;

import com.example.shortneer.repository.URLRepository;
import util.GenerateRandomToken;

public class URLService {
    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public void TestPrintResult(){
        String result = GenerateRandomToken.Build();

        System.out.println(result);
    }


}
