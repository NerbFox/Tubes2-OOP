package org.posapp.controller.currency;//package org.posapp.view.settings;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
import java.lang.reflect.Method;

public class FormatCurrency extends Pane {
    private Object Object_CW;
    private Class<?> CW;
    private Method methods[];
    private Field fields[];
    public FormatCurrency() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.Object_CW = null;
        this.CW = Class.forName("org.posapp.controller.currency.CurrencyWrapper");
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

        });
        usd.setOnAction(event -> {
            labelFormat.setText("USD");
            try {
                this.setPluginCurrency(new USDCurrency());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        yen.setOnAction(event -> {
            labelFormat.setText("JPY");
            try {
                this.setPluginCurrency(new JPYCurrency());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        krw.setOnAction(event -> {
            labelFormat.setText("KRW");
            try {
            this.setPluginCurrency(new KRWCurrency());
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
    private void setPluginCurrency(Object PluginCurrency) throws IllegalAccessException {
        printExample();
        // find attribute Currency
        for (Field field : fields) {
            System.out.println("field.getName() = " + field.getName());
            if (field.getName().equals("currency")) {
                System.out.println("Currency");
                field.setAccessible(true);
//                try {
//                    field.set(this.Object_CW, PluginCurrency);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
                field.set(this.Object_CW, PluginCurrency);
            }
            printExample();
        }
    }

//    public addCurrendy(){}
}
