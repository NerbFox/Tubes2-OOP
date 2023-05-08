package org.posapp.controller.currency;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class mainExample {
    private Object Object_CW;
    private Class<?> CW;
    private Method methods[];
    private Field fields[];
    public mainExample() throws ClassNotFoundException {
        this.Object_CW = null;
        this.CW = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
        this.methods = this.CW.getDeclaredMethods();
        this.fields = this.CW.getDeclaredFields();
        setUp();
    }
    private void setUp(){
        try {
            for (Method method1 : this.methods) {
                if (method1.getName().equals("getInstance")) {
                    System.out.println("getInstance");
                    method1.setAccessible(true);
                    // invoke the method
                    this.Object_CW = method1.invoke(null);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();} catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    private void printExample(){
        Float amount = 5000.0F;
        // find method getConvertedCurrency
        try {
            for (Method method1 : methods) {
                if (method1.getName().equals("getConvertedCurrency")) {
                    System.out.println("getConvertedCurrency");
                    method1.setAccessible(true);
                    // invoke the method
                    System.out.println("---> Currency = " + method1.invoke(Object_CW, amount));
                }
                else if(method1.getName().equals("getCodeCurrency")){
                    System.out.println("getCodeCurrency");
                    method1.setAccessible(true);
                    // invoke the method
                    System.out.println("---> Code Currency = " + method1.invoke(Object_CW));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    private void setPluginCurrency(Object PluginCurrency) throws IllegalAccessException, InvocationTargetException {
        // find attribute Currency
//        for (Field field : fields) {
//            System.out.println("field.getName() = " + field.getName());
//            if (field.getName().equals("currency")) {
//                System.out.println("Currency");
//                field.setAccessible(true);
//                // change Currency to JPYCurrency in singleton class CW
//                field.set(this.Object_CW, PluginCurrency);
////                    System.out.println("Converted Currency = " + CurrencyWrapper.getInstance().getConvertedCurrency(amount));
//            }
//        }
        // method setCurrency
        for (Method method1 : methods) {
            if (method1.getName().equals("setCurrency")) {
                System.out.println("setCurrency");
                method1.setAccessible(true);
                // invoke the method
                method1.invoke(Object_CW, PluginCurrency);
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        mainExample m = new mainExample();
        System.out.println("Hello world!");
//        Float amount = 5000.0F;
//        System.out.println("amount = " + amount);
//        System.out.println("amount after convert = " + CurrencyWrapper.getInstance().getConvertedCurrency(amount));
        m.printExample();
        m.setPluginCurrency(new USDCurrency());
        m.printExample();
//        while (true) {
//            try {
//                Thread.sleep(1000);
//                System.out.println("amount after convert = " + CurrencyWrapper.getInstance().getConvertedCurrency(amount));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            // find JPYCurrency class that must implement Currency interface
////            Class<? extends Currency> CJPY = Class.forName("org.posapp.controller.Currency.JPYCurrency").asSubclass(Currency.class);
////            Object Object_CJPY = CJPY.getDeclaredConstructor().newInstance();
//            Class<?> CW = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
//            Object o = null;
//            Method method[] = CW.getDeclaredMethods();
//            for (Method method1 : method) {
//                if (method1.getName().equals("getInstance")) {
//                    System.out.println("getInstance");
//                    method1.setAccessible(true);
//                    // invoke the method
//                    o = method1.invoke(null);
//                }
//            }
//            // find method getConvertedCurrency
//            for (Method method1 : method) {
//                if (method1.getName().equals("getConvertedCurrency")) {
//                    System.out.println("getConvertedCurrency");
//                    method1.setAccessible(true);
//                    // invoke the method
//                    System.out.println("Before Currency = " + method1.invoke(o, amount));
//                }
//            }
//            Field[] fields = CW.getDeclaredFields();
//            // find attribute currency
//            for (Field field : fields) {
//                System.out.println("field.getName() = " + field.getName());
//                if (field.getName().equals("currency")) {
//                    System.out.println("Currency");
//                    field.setAccessible(true);
//                    // change Currency to JPYCurrency in singleton class CW
//                    field.set(o, new DefaultCurrency());
//                }
//            }
//            // find method getConvertedCurrency
//            for (Method method1 : method) {
//                if (method1.getName().equals("getConvertedCurrency")) {
//                    System.out.println("getConvertedCurrency");
//                    method1.setAccessible(true);
//                    // invoke the method
//                    System.out.println("Converted Currency = " + method1.invoke(o, amount));
//                }
//            }
//
//        } catch (ClassNotFoundException | IllegalAccessException e) {
//            e.printStackTrace();} catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
    }
}
