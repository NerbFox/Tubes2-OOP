//package org.posapp.controller.

public class JPYCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return amount * 0.0093f;
    }
    public String getCodeCurrency() {
        return "JPY";
    }
}
