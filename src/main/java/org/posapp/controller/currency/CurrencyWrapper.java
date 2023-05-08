//package org.posapp.controller.currency;
package org.posapp.controller.currency;
//import org.posapp.controller.currency.Currency;
//import org.posapp.controller.currency.DefaultCurrency;

public class CurrencyWrapper implements Currency {
    private static CurrencyWrapper instance;
//    private Object currency;

    private Float Rate;

    private String CodeCurrency;

    private CurrencyWrapper() {
        // private constructor to prevent external instantiation
//        this.currency = new DefaultCurrency(); // set the default currency
        this.Rate = 1f;
        this.CodeCurrency = "IDR";
    }
    public static CurrencyWrapper getInstance() {
        if (instance == null) {
            instance = new CurrencyWrapper();
        }
        return instance;
    }

    public Float getConvertedCurrency(Float amount) {
//        return ((Currency) currency).getConvertedCurrency(amount);
        return amount * this.Rate;
    }
    public String getCodeCurrency() {
//        return ((Currency) currency).getCodeCurrency();
        return this.CodeCurrency;
    }
    public Float getConvertedCurrencyBack(Float amount) {return currency.getConvertedCurrencyBack(amount);}

//    public void setCurrency(Object currency) {
//        System.out.println("setCurrency !!!!!");
////        System.out.println("currency before = " + getConvertedCurrency(1000f));
//        this.currency = currency;
////        System.out.println("
//        // test
////        System.out.println("getConvertedCurrency(1000) = " + currency.getConvertedCurrency(1000f));
//    }

    public void setRate(Float rate) {
        System.out.println("setRate !!!!!");
        System.out.println("rate = " + this.Rate);
        this.Rate = rate;
        System.out.println("rate after = " + this.Rate);
    }
    public void setCode(String codeCurrency) {
        System.out.println("setCodeCurrency !!!!!");
        System.out.println("codeCurrency = " + this.CodeCurrency);
        this.CodeCurrency = codeCurrency;
        System.out.println("codeCurrency after = " + this.CodeCurrency);
    }
}
