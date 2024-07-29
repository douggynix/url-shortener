package com.doug.service;

import com.doug.bean.UrlCodec;
import com.doug.model.Url;
import com.doug.repository.UrlRepository;
import com.doug.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class UrlService {
    private static final long SEQUENCE = 8909837372L;
    final UrlRepository urlRepo;
    final UrlCodec urlCodec;

    public UrlService(UrlRepository urlRepository, UrlCodec urlCodec) {
        this.urlRepo = urlRepository;
        this.urlCodec = urlCodec;
    }

    @Transactional
    public String createShortUrl(String fullUrl){
        final MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        final byte  hashBytes[] = messageDigest.digest(fullUrl.getBytes());
        String urlHash = Utils.toHex(hashBytes);
        final var findResult = urlRepo.findByUrlHash(urlHash);

        final var url = findResult.orElseGet(() -> {
            final Url newUrl = new Url();
            newUrl.setFullUrl(fullUrl);
            newUrl.setUrlHash(urlHash);

            urlRepo.save(newUrl);
            log.info("Persiting new url : {}", newUrl);
            return newUrl;
        });

        String shortUrl = urlCodec.encode(url.getId() + SEQUENCE);
        return String.format("http://localhost:8080/%s",shortUrl);
    }



}
