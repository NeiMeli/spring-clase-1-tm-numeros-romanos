package com.example.clase1tmnumerosromanos.service;

import com.example.clase1tmnumerosromanos.common.RomanConversionSample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class RomanNumeralConverterServiceTest {

    @Autowired
    RomanNumeralConverterService service;

    @Test
    void testConvertHappy() throws Exception {
        RomanConversionSample[] samples = RomanConversionSample.getSamplesArray();
        for (RomanConversionSample sample : samples) {
            assertConversion(sample);
        }
    }

    @Test
    void testValidateFails() {
        assertValidateFails(0, RomanNumeralConverterError.ZERO);
        assertValidateFails(-15, RomanNumeralConverterError.NEGATIVE_NUMBER);
        assertValidateFails(RomanNumeralConverterService.MAX_DECIMAL_NUMBER + 1, RomanNumeralConverterError.MAX_DECIMAL_NUMBER_SURPASSED);
    }

    private void assertValidateFails(int decimalInput, RomanNumeralConverterError expectedError) {
        assertThatExceptionOfType(RomanNumeralConverterException.class)
                .isThrownBy(() -> service.convert(decimalInput))
                .withMessageContaining(expectedError.getValue());
    }

    private void assertConversion(RomanConversionSample sample) throws Exception {
        assertThat(service.convert(sample.decimalInput)).isEqualTo(sample.romanConversion);
    }
}