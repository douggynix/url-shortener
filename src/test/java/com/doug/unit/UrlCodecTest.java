package com.doug.unit;

import static org.junit.jupiter.api.Assertions.*;

import com.doug.bean.UrlCodec;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
public class UrlCodecTest {

  private UrlCodec urlCodec;

  @BeforeEach
  void setup() {
    urlCodec = new UrlCodec();
  }

  @ParameterizedTest
  @CsvSource(
      value = {"0:a", "25:z", "26:A", "51:Z", "52:0", "61:9", "62:ba", "63:bb", "64:bc", "65:bd"},
      delimiter = ':')
  void testEncoder(Integer input, String expectedSymbols) {
    String symbols = urlCodec.encode(input);
    assertEquals(expectedSymbols, symbols);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = ':',
      value = {"a:0", "b:1", "0:52", "9:61", "ba:62", "bb:63", "bd:65"})
  void testDecoder(String shortUrl, Long expectedId) {
    final var decodeResult = urlCodec.decode(shortUrl);
    assertTrue(decodeResult.isPresent());
    Long urlId = decodeResult.get();
    assertEquals(expectedId, urlId);
  }

  @ParameterizedTest
  @ValueSource(strings = {"dd_ls", "d97-98"})
  void testNonAlphumeric(String shortUrl) {
    final var decodeResult = urlCodec.decode(shortUrl);
    assertTrue(decodeResult.isEmpty());
  }

  @Test
  void testDecoder() {
    final var result = urlCodec.decode("jS8VSr");
    log.info("result = {}", result.get());
  }
}
