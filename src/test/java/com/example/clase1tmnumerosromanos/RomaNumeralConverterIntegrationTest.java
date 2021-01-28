package com.example.clase1tmnumerosromanos;

import com.example.clase1tmnumerosromanos.common.RomanConversionSample;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterError;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RomaNumeralConverterIntegrationTest {
    private static final String PATH = "/roman/convert/{value}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHappy() throws Exception {
        RomanConversionSample[] samples = RomanConversionSample.getSamplesArray();
        for (RomanConversionSample sample : samples) {
            mockMvc.perform(get(PATH, sample.getDecimalInputAsString()))
                    .andDo(print())
                    .andExpect(status().is(HttpStatus.OK.value()))
                    .andExpect(content().string(containsString(sample.romanConversion)));
        }
    }

    @Test
    public void testBadRequestCases() throws Exception {
        // not an integer
        mockMvc.perform(get(PATH,"not-an-integer"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterError.NOT_AN_INTENGER.getValue())));

        // input zero
        mockMvc.perform(get(PATH, "0"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterError.ZERO.getValue())));

        // input negative
        mockMvc.perform(get(PATH, "-34"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterError.NEGATIVE_NUMBER.getValue())));

        // input surpasses max
        mockMvc.perform(get(PATH, RomanNumeralConverterService.MAX_DECIMAL_NUMBER + 1))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterError.MAX_DECIMAL_NUMBER_SURPASSED.getValue())));
    }
}
