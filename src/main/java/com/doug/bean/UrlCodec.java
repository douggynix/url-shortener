package com.doug.bean;

import com.doug.symbols.SymbolsGenerator;
import com.doug.utils.Utils;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("urlCodec")
public class UrlCodec {

    final Map<Integer, String> symbols;
    final Map<String, Integer> symbolsValueMap;

    public UrlCodec(){
        var symbolGenerator = new SymbolsGenerator();
        symbols = symbolGenerator.generateSymbols();
        symbolsValueMap = symbols.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey)
        );
    }
    public String encode(long urlId){

        if(urlId == 0){
            return symbols.get(0);
        }

        int numBase = this.symbols.size();

        StringBuffer buffer = new StringBuffer();
        long currentValue = urlId;
        while ( currentValue > 0 ){
            Integer symbolIndex = (int) (currentValue % numBase);
            buffer.append(symbols.get(symbolIndex));
            currentValue = currentValue/numBase;
        }

        return buffer.reverse().toString();
    }

    public Optional<Long> decode(String shortUrl){
        if( !Utils.isAlphanumeric(shortUrl) ){
            return Optional.empty();
        }
        long numBase = this.symbols.size();
        Long urlId = 0L;
        int strLen = shortUrl.length();
        for(int i = 0; i< strLen; i++){
            String strValue = shortUrl.substring(i,i+1);
            long digitValue = symbolsValueMap.get(strValue);

            urlId = digitValue * (long) Math.pow(numBase,strLen-i-1) + urlId;
        }

        return Optional.of(urlId);
    }
}
