package com.example.clase1tmnumerosromanos.controller;

import com.example.clase1tmnumerosromanos.common.RomanNumeralConverterErrorForTest;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterService;
import com.example.clase1tmnumerosromanos.service.RomanNumeralConverterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RomanNumeralConverterControllerTest {
    private static final String PATH = "/roman/convert/{value}";
    private static final String DECIMAL_INPUT = "10";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RomanNumeralConverterService service;

    @Test
    public void testHappy() throws Exception {
        final String result = "XVI";
        when(service.convert(anyInt())).thenReturn(result);
        mockMvc.perform(get(PATH, DECIMAL_INPUT))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().string(containsString(result)));
    }

    @Test
    public void testBadRequest() throws Exception {
        when(service.convert(anyInt())).thenThrow(new RomanNumeralConverterException(RomanNumeralConverterErrorForTest.ERROR));
        mockMvc.perform(get(PATH, DECIMAL_INPUT))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterErrorForTest.ERROR.getValue())));
    }

    @Test
    public void testInternalServerError() throws Exception {
        when(service.convert(anyInt())).thenThrow(new RuntimeException(RomanNumeralConverterErrorForTest.ERROR.getValue()));
        mockMvc.perform(get(PATH, DECIMAL_INPUT))
                .andDo(print())
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(content().string(containsString(RomanNumeralConverterErrorForTest.ERROR.getValue())));
    }
}