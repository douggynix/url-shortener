package com.doug.integrated;

import com.doug.bean.UrlCodec;
import com.doug.repository.UrlRepository;
import com.doug.service.UrlService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    UrlCodec urlCodec(){
        return new UrlCodec();
    }

    @Bean
    UrlService urlService(UrlRepository urlRepo, UrlCodec urlCodec){
        return new UrlService(urlRepo, urlCodec);
    }
}
