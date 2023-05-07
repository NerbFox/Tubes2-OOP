package org.posapp.controller.currency;

// static class
public class DefaultCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return (float) (amount * 0.5);
    }

    public Float getConvertedCurrencyBack(Float amount) {
        return (float) (amount * (1/0.5));
    }

    public String getCodeCurrency() {
        return "IDR";
    }
}
