package com.example.clase1tmnumerosromanos.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RomanNumeralConverterService {
    public static final int MAX_DECIMAL_NUMBER = 3999;
    private static final Map<String, Integer> map = new LinkedHashMap<>();
    static {
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("C", 100 );
        map.put("XC", 90);
        map.put("L", 50 );
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
    }

    public String convert(final int decimalInput) throws RomanNumeralConverterException {
        validate(decimalInput);
        AtomicInteger rest = new AtomicInteger(decimalInput);
        final StringBuilder result = new StringBuilder();
        map.forEach((romanLetter, decimalEquivalent) -> {
            int restValue = rest.get();
            while (restValue >= decimalEquivalent) {
                restValue -= decimalEquivalent;
                result.append(romanLetter);
            }
            rest.set(restValue);
        });
        return result.toString();
    }

    private void validate(int decimalInput) throws RomanNumeralConverterException {
        if (decimalInput == 0) throw new RomanNumeralConverterException(RomanNumeralConverterError.ZERO);
        if (decimalInput < 0) throw new RomanNumeralConverterException(RomanNumeralConverterError.NEGATIVE_NUMBER);
        if (decimalInput > MAX_DECIMAL_NUMBER) throw new RomanNumeralConverterException(RomanNumeralConverterError.MAX_DECIMAL_NUMBER_SURPASSED);
    }
}
