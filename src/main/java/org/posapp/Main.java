package org.posapp;

import org.posapp.view.DisplayManager;


/**
 * JavaFX App
 */

//public class Main extends Application {
//
//    @Override
//    public void start(Stage stage) {
//        Object javaVersion = "8";
//        Object javafxVersion = "8";
//        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        Scene scene = new Scene(new StackPane(label), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//}

public class Main {
    public static void main(String[] args){
        DisplayManager displayManager = new DisplayManager();

        displayManager.play();
    }
}