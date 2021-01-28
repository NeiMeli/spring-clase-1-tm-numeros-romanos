package com.example.clase1tmnumerosromanos.common;

public class RomanConversionSample {
    public static final RomanConversionSample SAMPLE1 = new RomanConversionSample(1, "I");
    public static final RomanConversionSample SAMPLE2 = new RomanConversionSample(10, "X");
    public static final RomanConversionSample SAMPLE3 = new RomanConversionSample(7, "VII");
    public static final RomanConversionSample SAMPLE4 = new RomanConversionSample(15, "XV");
    public static final RomanConversionSample SAMPLE5 = new RomanConversionSample(278, "CCLXXVIII");
    public static final RomanConversionSample SAMPLE6 = new RomanConversionSample(899, "DCCCXCIX");
    public static final RomanConversionSample SAMPLE7 = new RomanConversionSample(3052, "MMMLII");
    public static final RomanConversionSample SAMPLE8 = new RomanConversionSample(3999, "MMMCMXCIX");

    public final int decimalInput;
    public final String romanConversion;

    public RomanConversionSample(int decimalInput, String romanConversion) {
        this.decimalInput = decimalInput;
        this.romanConversion = romanConversion;
    }

    public static RomanConversionSample[] getSamplesArray() {
        return new RomanConversionSample[] {SAMPLE1, SAMPLE2, SAMPLE3, SAMPLE4, SAMPLE5, SAMPLE6, SAMPLE7, SAMPLE8};
    }

    public String getDecimalInputAsString() {
        return String.valueOf(decimalInput);
    }
}
