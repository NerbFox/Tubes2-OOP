//package org.posapp.controller.

public class USDCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return amount * 0.000068f;
    }
    public String getCodeCurrency() {
        return "USD";
    }
}
