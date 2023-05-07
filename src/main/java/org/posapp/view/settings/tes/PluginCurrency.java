//package org.posapp.view.settings.tes;//package org.posapp.view.settings;
// reflection
//import org.posapp.view.settings.format_file;

//import org.posapp.view.settings.FormatCurrency;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
// label
//import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
//import org.posapp.view.settings.FormatCurrency;

public class PluginCurrency {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
        // change rate
    public static void RunPlugin(Object borderPane) {
        System.out.println("Hello world!, sudah bisa nih?? apakah bisa dijalankan? ini emang bisa... ");
//        while(true){
//        }
        // reflection, find classes
        try {
            Class<?> c = Class.forName("org.posapp.view.settings.tab_settings");
//            Constructor<?> constructor = c.getConstructor(String.class);
//            Object o = constructor.newInstance("contoh");

//            Class<?> c2 = Class.forName("org.posapp.view.settings.format_file");
            // find method addButtonAndTab("contoh", new plugin_settings());
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("addButtonAndTab")) {
                    System.out.println("addButtonAndTab");
                    method.setAccessible(true);
                    // invoke the method
                    method.invoke(borderPane, "Currency Settings", new FormatCurrency());
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
