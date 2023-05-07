package org.posapp.view.settings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UsdConverter implements CurrencyConverter {
    // reflection, find all the classes that has a variabel price
    // change the price to the new price with convert method
    public UsdConverter() {
        System.out.println("UsdConverter");
    }
    public double convert(double amount) {
        return amount * 0.1;
    }
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("org.posapp.view.settings.exampleUseCurrency");
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
                System.out.println(field.getType());
                if (field.getType().equals(double.class)) {
                    System.out.println("double");
                    field.setAccessible(true);
                    // there is no setter and getter
                    // so we need to use reflection
                    // to change the value of the field
//                    field.setDouble(field.get, 100.0);
                }
            }
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
                System.out.println(method.getReturnType());
                System.out.println(method.getParameterCount());
                if (method.getName().equals("getAmount")) {
                    System.out.println("getAmount");
                    method.setAccessible(true);
                    // change the return value of the method
                    // to the new value

//                    System.out.println(method.invoke(c));
                }
            }

//            Constructor<?> ctor = c.getConstructor();
//            Object object = ctor.newInstance();
//            System.out.println(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

