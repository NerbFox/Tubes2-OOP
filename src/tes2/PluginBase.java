import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
// label
//import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PluginBase {
    public void PluginBase() {
        System.out.println("PluginBase");
    }
    public static void RunPlugin(Object borderPane){
        System.out.println("Hello world!, sudah bisa");
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
//                    try {
                    method.invoke(borderPane, "Base Plugin", new Base());
//                    } catch (IllegalAccessException | NoSuchMethodException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}