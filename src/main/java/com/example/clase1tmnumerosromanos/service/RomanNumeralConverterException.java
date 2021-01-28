package com.example.clase1tmnumerosromanos.service;

public class RomanNumeralConverterException extends Exception {
    public RomanNumeralConverterException(RomanNumeralConverterErrorInterface error) {
        super(error.getValue());
    }
}

