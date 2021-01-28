package com.example.clase1tmnumerosromanos.common;

import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterErrorInterface;

public enum RomanNumeralConverterErrorForTest implements RomanNumeralConverterErrorInterface {
    ERROR;
    @Override
    public String getValue() {
        return "error";
    }
}
