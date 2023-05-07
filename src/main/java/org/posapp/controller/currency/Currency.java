package org.posapp.controller.currency;

public interface Currency {
    public Float getConvertedCurrency(Float amount);
    public Float getConvertedCurrencyBack(Float amount);

    public String getCodeCurrency();
}
