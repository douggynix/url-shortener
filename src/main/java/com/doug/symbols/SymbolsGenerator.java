package com.doug.symbols;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SymbolsGenerator {
    public Map<Integer,String> generateCharMap(char startChar, char endChar, int offset ){
        return IntStream.rangeClosed(startChar, endChar)
                .boxed()
                .collect(Collectors.toMap(key -> Integer.valueOf(key - startChar + offset), value -> charToString( (char) value.intValue() )));
    }
    private String charToString(char ch){
        return String.valueOf(ch);
    }

    public Map<Integer, String> generateSymbols(){
        final Map<Integer,String> smallLettersMap= generateCharMap('a','z',0);

        final Map<Integer, String> capitalLettersMap = generateCharMap('A','Z', smallLettersMap.size());

        final Map<Integer,String> allLetters = Stream.concat(smallLettersMap.entrySet().stream(), capitalLettersMap.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final Map<Integer, String> digitsMap =
                generateCharMap('0', '9', allLetters.size());


        final var symbolsMap =  Stream.concat(allLetters.entrySet().stream(), digitsMap.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return symbolsMap;
    }

}
