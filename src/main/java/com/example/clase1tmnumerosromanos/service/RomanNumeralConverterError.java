package com.example.clase1tmnumerosromanos.service;

public enum RomanNumeralConverterError implements RomanNumeralConverterErrorInterface {
    NEGATIVE_NUMBER ("No se pueden convertir números negativos a romanos"),
    ZERO ("No se puede convertir el cero a romano"),
    MAX_DECIMAL_NUMBER_SURPASSED (String.format("No se puede convertir un numero mayor a %s", RomanNumeralConverterService.MAX_DECIMAL_NUMBER)),
    NOT_AN_INTENGER("No ingreso un numero entero");

    private final String value;

    RomanNumeralConverterError(String s) {
        value = s;
    }
    @Override public String getValue() { return value; }

}

