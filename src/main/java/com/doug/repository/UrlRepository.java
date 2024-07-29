package com.doug.repository;

import com.doug.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository  extends JpaRepository<Url,Long> {

    Optional<Url> findByUrlHash(String urlHash);
}
