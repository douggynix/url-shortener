package com.doug.integrated;

import static org.junit.jupiter.api.Assertions.*;

import com.doug.repository.UrlRepository;
import com.doug.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(value = {TestConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UrlServiceTest {

  private final String fullUrl = "http://google.com/search";
  private final String expectedShortUrl = "http://localhost:8080/jS8VSz";
  private final String expectedHash =
      "BC63B9AABAACD4B6CF28E1ADD79F95EDE628D9A4FD347F9EC2A05B823A4D75";
  @Autowired private UrlService urlService;

  @Autowired private UrlRepository urlRepo;

  @Test
  @Order(1)
  void testCreateShortUrl() {
    final var shortUrlDto = urlService.createShortUrl(fullUrl);
    log.info("short url = {}", shortUrlDto);
    assertEquals(expectedShortUrl, shortUrlDto.shortUrl());
  }

  @Test
  @Order(2)
  void testCreateDuplicateUrl() {
    final var shortUrlDto = urlService.createShortUrl(fullUrl);
    log.info("Short url duplicate result = {}", shortUrlDto);
    assertEquals(1, urlRepo.findAll().stream().count());
  }

  @Test
  @Order(3)
  void testDecodeShortUrl() {
    final var result = urlService.retrieveUrl("jS8VSz");
    assertTrue(result.isPresent());
    final var urlDto = result.get();
    assertEquals(fullUrl, urlDto.url());
  }

  @Test
  @Order(4)
  void testDecodeInvalidUrl() {
    final var result = urlService.retrieveUrl("bd1JV");
    assertTrue(result.isEmpty());
  }
}
