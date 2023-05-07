package org.posapp.controller.currency;

// static class
public class DefaultCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return amount;
    }
    public String getCodeCurrency() {
        return "IDR";
    }
}
