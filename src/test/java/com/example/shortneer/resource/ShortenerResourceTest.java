package com.example.shortneer.resource;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.shortneer.domain.URL;
import com.example.shortneer.repository.URLRepository;

import util.GenerateRandomToken;

@SpringBootTest
@AutoConfigureMockMvc
class ShortenerResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private URLRepository urlRepository;
    @Test
  void testShortenerGetEndpointFailCase() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/shortener"))
       .andExpect(MockMvcResultMatchers.status()
       .isBadRequest())
       .andExpect(MockMvcResultMatchers.content()
       .contentType("application/json"))
       .andExpect(MockMvcResultMatchers
       .jsonPath("message")
       .value("Invalid Parameter"))
       .andReturn();
  }

  @Test
  void testShortenerGetEndpointSuccessCase() throws Exception {
    URL url = new URL();
    url.setOriginalUrl("https://www.google.com");
    
    url.setShortUrl("http://localhost:8080/api/v1/shortener?token=" + GenerateRandomToken.build());
    url.setExpiresAt(LocalDateTime.now().plusMinutes(30));
    this.urlRepository.save(url);
    mockMvc.perform(MockMvcRequestBuilders.get(url.getShortUrl()))
       .andExpect(MockMvcResultMatchers.status()
       .isFound());
  }


}
