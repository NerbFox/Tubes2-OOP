package org.posapp.controller;

// static class
public class Converter {
    // attribute rate
    private static Double Rate = 1.0;
    // string code currency
    private static String codeCurrency = "IDR";
    public static Double convert(double amount) {
        // base to other and other to base
        return amount * Rate;
    }
    public static String getCodeCurrency() {
        return codeCurrency;
    }
    public static void main(String[] args) {
        Double amount = 5000.0;
        System.out.println("amount = " + amount);
        System.out.println("amount after convert = " + convert(amount));
    }
}
