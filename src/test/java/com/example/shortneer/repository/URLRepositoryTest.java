package com.example.shortneer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.shortneer.domain.URL;
import jakarta.persistence.EntityManager;



@DataJpaTest
class URLRepositoryTest {
    
    @Autowired
    EntityManager entityManager;

    @Autowired
    URLRepository urlRepository;

    URL url = new URL();

@BeforeEach
void setUp() {
    
    this.url.setExpiresAt(LocalDateTime.now());
    this.url.setOriginalUrl("https:w");
    this.url.setShortUrl("http:test/?token=xyz");
    
    urlRepository.save(this.url);
}
   
    
@Test
void findByShortUrlSuccessTest() throws RuntimeException {
        boolean flag = this.urlRepository.findByShortUrl(url.getShortUrl()).isPresent();
        assertEquals(true, flag);
    
  }
  @Test
void findByShortUrlFailTest() throws RuntimeException {
        boolean flag = this.urlRepository.findByShortUrl("http:tt.com/xaz").isPresent();
        assertEquals(false, flag);
    
  }

}
