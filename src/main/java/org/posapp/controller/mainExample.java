package org.posapp.controller;


import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import java.lang.reflect.Field;

public class mainExample {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Double amount = 5000.0;
        System.out.println("amount = " + amount);
        System.out.println("amount after convert = " + Converter.convert(amount));
        // change rate
//        Converter.Rate = 0.0001;
//        System.out.println("amount after convert = " + Converter.convert(amount));
        // using reflection
        try {
            Class<?> c = Class.forName("org.posapp.controller.Converter");
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("codeCurrency")) {
                    System.out.println("codeCurrency");
                    field.setAccessible(true);
                    // change codeCurrency
                    field.set(null, "USD");
                    System.out.println("Code Currency = " + Converter.getCodeCurrency());
                } else
                if (field.getName().equals("Rate")) {
                    System.out.println("Rate");
                    field.setAccessible(true);
                    // change rate
                    field.set(null, 0.0001);
                    // change codeCurrency
                    System.out.println("amount after convert = " + Converter.convert(amount));
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
