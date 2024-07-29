package com.doug.integrated;

import static org.junit.jupiter.api.Assertions.*;

import com.doug.repository.UrlRepository;
import com.doug.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import( value = {TestConfig.class})
@SpringBootTest
public class UrlServiceTest {

    private final String fullUrl = "http://google.com/search";
    private final String expectedShortUrl = "http://localhost:8080/jS8VSr";
    private final String expectedHash = "BC63B9AABAACD4B6CF28E1ADD79F95EDE628D9A4FD347F9EC2A05B823A4D75";
    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlRepository urlRepo;
    @Test
    void testCreateShortUrl(){
        String shortUrl = urlService.createShortUrl(fullUrl);
        log.info("short url = {}", shortUrl);
        assertEquals(expectedShortUrl,shortUrl);
    }

    @Test
    void testCreateDuplicateUrl(){
        String shortUrl = urlService.createShortUrl(fullUrl);
        assertEquals(1, urlRepo.findAll().stream().count());
    }
}
