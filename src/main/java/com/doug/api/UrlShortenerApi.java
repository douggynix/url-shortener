package com.doug.api;

import com.doug.model.UrlDto;
import com.doug.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UrlShortenerApi {
    final UrlService urlService;

    UrlShortenerApi(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/api/createurl")
    ResponseEntity createUrl(@RequestBody UrlDto urlDto){
        try {
            final var shortUrlDto = this.urlService.createShortUrl(urlDto.url());
            log.info("Create short url for '{}' ->  '{}'",urlDto.url(), shortUrlDto.shortUrl());
            return ResponseEntity.status(HttpStatus.OK).body(shortUrlDto);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server failed Processing request. Check Server log");
        }
    }

    @GetMapping("/{shortUrl}")
    ResponseEntity retrieveUrl(@PathVariable("shortUrl") String shortUrlValue){
        try{
            final var urlResult = this.urlService.retrieveUrl(shortUrlValue);
            return urlResult.isPresent()
                    ? ResponseEntity.status(HttpStatus.OK).body(urlResult.get())
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("This short url '%s' not found",shortUrlValue));
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server failed Processing request. Check Server log");
        }

    }

}
