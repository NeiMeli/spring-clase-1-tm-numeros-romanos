package com.example.clase1tmnumerosromanos.controller;

import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterError;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterService;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roman")
public class RomanNumeralConverterController {

    @Autowired
    RomanNumeralConverterService service;

    @GetMapping("/convert/{value}")
    public ResponseEntity<String> convert(@PathVariable String value) {
        String response;
        HttpStatus status;
        try {
            int number = getIntValue(value);
            response = service.convert(number);
            status = HttpStatus.OK;
        } catch (final Exception e) {
            if (e instanceof RomanNumeralConverterException) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            response = e.getMessage();
        }
        return new ResponseEntity<>(response, status);
    }

    private int getIntValue(String value) throws RomanNumeralConverterException {
        try {
            return Integer.parseInt(value);
        } catch (final Exception e) {
            throw new RomanNumeralConverterException(RomanNumeralConverterError.NOT_AN_INTENGER);
        }
    }
}
