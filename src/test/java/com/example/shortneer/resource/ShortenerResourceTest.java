package com.example.shortneer.resource;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.shortneer.http.response.UrlResponse;
import com.example.shortneer.repository.URLRepository;
import com.example.shortneer.service.URLService;


@SpringBootTest
@AutoConfigureMockMvc
class ShortenerResourceTest {
    String uriTemplate = "http://localhost:8080/api/v1/shortener";
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private URLRepository urlRepository;

    @Autowired
    @InjectMocks
    URLService urlService;
    @Test
  void testShortenerPostEndpointFailCase() throws Exception {
    String json = "{\"url\":\"htt\"}";
    
    mockMvc.perform(MockMvcRequestBuilders.post(this.uriTemplate)
    .contentType("application/json;charset=UTF-8")
    .content(json))
    .andExpect(MockMvcResultMatchers.status().isBadRequest())
    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
    .andExpect(MockMvcResultMatchers.jsonPath("error").value("Validation URL exception")).andReturn();
       
  }

  @Test
  void testShortenerPostEndpointSuccessCase() throws Exception {
    String json = "{\"url\":\"http:www.google.com\"}";
    
    mockMvc.perform(MockMvcRequestBuilders.post(this.uriTemplate)
    .contentType("application/json;charset=UTF-8")
    .content(json))
    .andExpect(MockMvcResultMatchers.status().isCreated())
    .andExpect(MockMvcResultMatchers.content().contentType("application/json")).andReturn();
       
  }


    @Test
  void testShortenerGetEndpointFailCase() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(this.uriTemplate + "/1212121"))
       .andExpect(MockMvcResultMatchers.status()
       .isNotFound())
       .andExpect(MockMvcResultMatchers.content()
       .contentType("application/json"))
       .andExpect(MockMvcResultMatchers
       .jsonPath("message")
       .value("we couldn't find this shortUrl"))
       .andReturn();
  }

  @Test
  void testShortenerGetEndpointSuccessCase() throws Exception {
    
    UrlResponse response = this.urlService.save("https://www.google.com");
    
    mockMvc.perform(MockMvcRequestBuilders.get(response.shortUrl()))
       .andExpect(MockMvcResultMatchers.status()
       .isFound());
  }


}
