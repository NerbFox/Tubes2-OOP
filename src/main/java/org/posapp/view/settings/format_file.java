package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.geometry.Side;
import javafx.scene.control.ToolBar;
// jpopupmenu


import javax.swing.*;
// jbutton

public class format_file extends Pane {
    public format_file() {
        int width = 450;
        int height = 50;
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");

        // rectangle box with text No format selected
        Label labelFormat = new Label("No format selected");
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
        MenuItem jsonItem = new MenuItem("JSON");
        MenuItem xmlItem = new MenuItem("XML");
        // event handler
        jsonItem.setOnAction(event -> {
            labelFormat.setText("JSON");
        });
        xmlItem.setOnAction(event -> {
            labelFormat.setText("XML");
        });
        jsonItem.setStyle("-fx-pref-width: 428px;");
        xmlItem.setStyle("-fx-pref-width: 428px;");

        // setting up formatButton
        formatButton.setPrefSize(width, height);
        formatButton.setLayoutX(160);
        formatButton.setLayoutY(130);
        formatButton.setAlignment(Pos.CENTER);
        formatButton.getItems().addAll(jsonItem, xmlItem);
        formatButton.setPopupSide(Side.BOTTOM);
        getChildren().add(formatButton);


    }

}
