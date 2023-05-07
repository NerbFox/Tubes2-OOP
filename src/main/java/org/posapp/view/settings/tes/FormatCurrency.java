//package org.posapp.view.settings;
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

public class FormatCurrency extends Pane {
    public FormatCurrency() {
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
        MenuItem usd = new MenuItem("USD");
        MenuItem yen = new MenuItem("JPY");
        MenuItem krw = new MenuItem("KRW");
        // event handler
        usd.setOnAction(event -> {
            labelFormat.setText("USD");
        });
        yen.setOnAction(event -> {
            labelFormat.setText("JPY");
        });
        krw.setOnAction(event -> {
            labelFormat.setText("KRW");
        });
        usd.setStyle("-fx-pref-width: 428px;");
        yen.setStyle("-fx-pref-width: 428px;");
        krw.setStyle("-fx-pref-width: 428px;");

        // setting up formatButton
        formatButton.setPrefSize(width, height);
        formatButton.setLayoutX(160);
        formatButton.setLayoutY(130);
        formatButton.setAlignment(Pos.CENTER);
        formatButton.getItems().addAll(usd, yen, krw);
        formatButton.setPopupSide(Side.BOTTOM);
        getChildren().add(formatButton);


    }

}
