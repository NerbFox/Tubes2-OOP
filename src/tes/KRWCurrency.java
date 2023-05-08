//package org.posapp.controller.

public class KRWCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return amount * 0.090f;
    }
    public String getCodeCurrency() {
        return "KRW";
    }
}
