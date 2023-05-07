package org.posapp.controller.currency;

public interface Currency {
    public Float getConvertedCurrency(Float amount);

    public String getCodeCurrency();
}
