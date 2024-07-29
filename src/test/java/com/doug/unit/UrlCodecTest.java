package com.doug.unit;

import static org.junit.jupiter.api.Assertions.*;

import com.doug.bean.UrlCodec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UrlCodecTest {

    private UrlCodec urlCodec;

    @BeforeEach
    void setup(){
        urlCodec = new UrlCodec();
    }

    @ParameterizedTest
    @CsvSource(value = {"0:a", "25:z", "26:A", "51:Z", "52:0", "61:9", "62:ba", "63:bb", "64:bc", "65:bd"}, delimiter = ':')
    void testEncoder(Integer input, String expectedSymbols){
        String symbols = urlCodec.encode(input);
        assertEquals(expectedSymbols, symbols);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {"a:0","b:1","0:52", "9:61","ba:62","bb:63","bd:65"})
    void testDecoder(String shortUrl, Long expectedId){
        long urlId = urlCodec.decode(shortUrl);
        assertEquals(expectedId, urlId);
    }

}
