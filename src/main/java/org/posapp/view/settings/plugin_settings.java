package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import java.awt.*;

public class plugin_settings extends Pane {
    public plugin_settings() {
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");

        // rectangle box with text No plugin loaded
        Label labelPlugin = new Label("No plugin loaded");
        // set box to width
        labelPlugin.setStyle("-fx-border-color: #666666; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #eeeeee; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;");

        labelPlugin.setPrefSize(450, 50);
        labelPlugin.setLayoutX(160);
        labelPlugin.setLayoutY(35);
        labelPlugin.setAlignment(Pos.CENTER);
        getChildren().add(labelPlugin);

        Button buttonLoadPlugin = new Button("Load Plugin");
        buttonLoadPlugin.setStyle("-fx-border-color: #555555; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #b9b9b9; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #ffffff;");

        buttonLoadPlugin.setPrefSize(350, 50);
        buttonLoadPlugin.setLayoutX(160);
        buttonLoadPlugin.setLayoutY(130);
        buttonLoadPlugin.setAlignment(Pos.CENTER);
        getChildren().add(buttonLoadPlugin);

        Button buttonXPlugin = new Button("X");
        buttonXPlugin.setStyle("-fx-border-color: #555555; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #b9b9b9; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #000000;");

        buttonXPlugin.setPrefSize(75, 50);
        buttonXPlugin.setLayoutX(535);
        buttonXPlugin.setLayoutY(130);
        buttonXPlugin.setAlignment(Pos.CENTER);
        getChildren().add(buttonXPlugin);



        // button box with text Load plugin



    }

}
