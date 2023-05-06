package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.awt.*;
import java.io.File;

public class plugin_settings extends Pane {
    // array of string path
    private List<String> path;
    public plugin_settings() {
        path = new ArrayList<>();
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
        buttonLoadPlugin.setOnAction(event -> {
            // set label text to indicate file selection
            labelPlugin.setText("Select a file");
            // set label text color to black
            labelPlugin.setTextFill(Color.BLACK);

            // create a new file chooser
            FileChooser fileChooser = new FileChooser();

            // set the title of the file chooser dialog
            fileChooser.setTitle("Open File");

            // show the file chooser dialog and wait for user selection
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if(selectedFile != null) {
                // do something with the selected file
                path.add(selectedFile.getAbsolutePath());
                // get the name of the selected file
                labelPlugin.setText(selectedFile.getName());
                System.out.println(path);
            }
        });
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
