package com.doug.bean;

import com.doug.symbols.SymbolsGenerator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

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
            Integer decimalBaseVal = (int) currentValue % numBase;
            buffer.append(symbols.get(decimalBaseVal));
            currentValue = currentValue/numBase;
        }

        return buffer.reverse().toString();
    }

    public long decode(String shortUrl){
        long numBase = this.symbols.size();
        long urlId = 0;
        int strLen = shortUrl.length();
        for(int i = 0; i< strLen; i++){
            String strValue = shortUrl.substring(i,i+1);
            int digitValue = symbolsValueMap.get(strValue);

            urlId = digitValue * (long) Math.pow(numBase,strLen-i-1 ) + urlId;
        }

        return urlId;
    }
}
