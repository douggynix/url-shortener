package com.doug.unit;

import static org.junit.jupiter.api.Assertions.*;

import com.doug.model.Url;
import com.doug.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UrlRepositoryTest {
    @Autowired
    private UrlRepository urlRepo;

    @Autowired
    private TestEntityManager entityManager;

    private Long urlId;

    @BeforeEach
    void setup(){
        Url url = new Url();
        url.setFullUrl("http://gmail.com/login");
        url.setUrlHash("SHA-256");
        entityManager.persist(url);
        urlId = url.getId();
    }

    @Test
    void testFindUrlWithRepository(){
        System.out.printf("urlId = %d",urlId);
        final var result = urlRepo.findById(urlId);
        assertFalse(result.isEmpty());
        final var url = result.get();
        System.out.println(url);
    }

    @Test
    void testFindUrlByHash(){
        final var findResult = urlRepo.findByUrlHash("SHA-256");
        assertFalse(findResult.isEmpty());
        final var url = findResult.get();
        assertEquals("SHA-256",url.getUrlHash());
    }
}
