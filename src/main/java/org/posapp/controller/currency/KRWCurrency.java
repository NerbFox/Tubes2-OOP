//package org.posapp.controller.currency;//package org.posapp.controller.
package org.posapp.controller.currency;
//import org.posapp.controller.currency.Currency;

public class KRWCurrency implements Currency {
    public Float getConvertedCurrency(Float amount) {
        // base to other and other to base
        return amount * 0.090f;
    }
    public String getCodeCurrency() {
        return "KRW";
    }
}
