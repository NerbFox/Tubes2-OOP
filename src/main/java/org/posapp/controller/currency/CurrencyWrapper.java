//package org.posapp.controller.currency;
package org.posapp.controller.currency;
//import org.posapp.controller.currency.Currency;
//import org.posapp.controller.currency.DefaultCurrency;

class CurrencyWrapper implements Currency {
    private static CurrencyWrapper instance;
    private Currency currency;

    private CurrencyWrapper() {
        // private constructor to prevent external instantiation
        this.currency = new DefaultCurrency(); // set the default currency
    }
    public static CurrencyWrapper getInstance() {
        if (instance == null) {
            instance = new CurrencyWrapper();
        }
        return instance;
    }

    public Float getConvertedCurrency(Float amount) {
        return currency.getConvertedCurrency(amount);
    }
    public String getCodeCurrency() {
        return currency.getCodeCurrency();
    }

}
