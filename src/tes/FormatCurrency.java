//package org.posapp.view.settings;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
import java.lang.reflect.Method;

public class FormatCurrency extends Pane {
//    private Object fCW;
    private Float Rate;
    private String Code;
    private Object Object_CW;
    private Class<?> CW;
    private Method methods[];
    private Field fields[];
    public FormatCurrency()  {
//        this.fCW = Ob;
//        this.Object_CW = null;
        try {
            this.CW = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        this.CW = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
        this.methods = this.CW.getDeclaredMethods();
        this.fields = this.CW.getDeclaredFields();
        setUp();
        int width = 450;
        int height = 50;
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");

        // rectangle box with text No format selected
        Label labelFormat = new Label("No format currency selected");
        labelFormat.setStyle("-fx-border-color: #666666; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #eeeeee; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;");
        labelFormat.setPrefSize(width, height);
        labelFormat.setLayoutX(160);
        labelFormat.setLayoutY(35);
        labelFormat.setAlignment(Pos.CENTER);
        getChildren().add(labelFormat);

        // Drop down format button
        MenuButton formatButton = new MenuButton("Select format file");
        formatButton.setStyle("-fx-border-color: #555555; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #b7b7b7; " +
                "-fx-padding: 8px;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #c7c7c7;");

        // MenuItems for formatButton
        MenuItem idr = new MenuItem("IDR");
        MenuItem usd = new MenuItem("USD");
        MenuItem yen = new MenuItem("JPY");
        MenuItem krw = new MenuItem("KRW");
        // event handler
        idr.setOnAction(event -> {
            labelFormat.setText("IDR");
            try {
                this.Rate = 1.0f;
                this.Code = "IDR";
                this.setPluginCurrency();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        usd.setOnAction(event -> {
            labelFormat.setText("USD");
            try {
                this.Rate = 0.000068f;
                this.Code = "USD";
                this.setPluginCurrency();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        yen.setOnAction(event -> {
            labelFormat.setText("JPY");
            try {
                this.Rate = 0.0093f;
                this.Code = "JPY";
                this.setPluginCurrency();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        krw.setOnAction(event -> {
            labelFormat.setText("KRW");
            try {
                this.Rate = 0.090f;
                this.Code = "KRW";
                this.setPluginCurrency();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
//        setPluginCurrency(new KRWCurrency());
        usd.setStyle("-fx-pref-width: 428px;");
        yen.setStyle("-fx-pref-width: 428px;");
        krw.setStyle("-fx-pref-width: 428px;");
        idr.setStyle("-fx-pref-width: 428px;");

        // setting up formatButton
        formatButton.setPrefSize(width, height);
        formatButton.setLayoutX(160);
        formatButton.setLayoutY(130);
        formatButton.setAlignment(Pos.CENTER);
        formatButton.getItems().addAll(idr, usd, yen, krw);
        formatButton.setPopupSide(Side.BOTTOM);
        getChildren().add(formatButton);

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
    private void setPluginCurrency() throws IllegalAccessException {
//        printExample();
        Float amount = 5000.0F;
        System.out.println("jjjj");
//        System.out.println("convertCurrency" + PluginCurrency.getConvertedCurrency(amount).toString());
//        System.out.println("convertCurrency" + this.fCW.getConvertedCurrency(amount).toString());
        try {
            Class<?> c = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
//            Constructor<?> constructor = c.getConstructor(String.class);
//            Object o = constructor.newInstance("contoh");

//            Class<?> c2 = Class.forName("org.posapp.view.settings.format_file");
            // find method addButtonAndTab("contoh", new plugin_settings());
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("setRate")) {
                    System.out.println("setRateCurrency...................");
                    method.setAccessible(true);
                    // invoke the method
//                    try {
                    method.invoke(this.Object_CW, this.Rate);
//                    } catch (IllegalAccessException | NoSuchMethodException e) {
//                        e.printStackTrace();
//                    }
                }
                else if(method.getName().equals("setCode")) {
                    System.out.println("setCodeCurrency...................");
                    method.setAccessible(true);
                    // invoke the method
                    method.invoke(this.Object_CW, this.Code);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(PluginCurrency.getConvertedCurrency(amount));
        // setRate method
//        for (Method method1 : methods) {
//            if (method1.getName().equals("setRate")) {
//                System.out.println("setRate");
//                method1.setAccessible(true);
//                // invoke the method
//                try {
//                    method1.invoke(this.fCW, PluginCurrency);
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println(this.fCW.getConvertedCurrency(amount));
//        this.Object_CW
//        for (Method method1 : methods) {
//            if (method1.getName().equals("setCurrency")) {
//                System.out.println("setCurrency");
//                method1.setAccessible(true);
//                // invoke the method
//                try {
//                    method1.invoke(this.Object_CW, PluginCurrency);
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
////                method1.invoke(this.fCW, PluginCurrency);
//            }
//        }
        System.out.println("bisaaaaaaaaaaaa");
//        printExample();
    }

//    public addCurrendy(){}
}
