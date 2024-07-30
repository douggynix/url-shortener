package com.doug.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name = "full_url",nullable = false)
   private String fullUrl;

   @Column(name = "url_hash", nullable = false, unique = true)
   private String urlHash;

   private OffsetDateTime createdAt = OffsetDateTime.now();
}
