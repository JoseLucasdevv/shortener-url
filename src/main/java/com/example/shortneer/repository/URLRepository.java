package com.example.shortneer.repository;

import com.example.shortneer.domain.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface URLRepository extends JpaRepository<URL,Long> {
    Optional<URL> findByShortUrl(String shortUrl);
}
