package org.posapp.view.cashier_menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.posapp.model.Barang;
import javafx.stage.Stage;

public class BillItem extends HBox {
    Barang barang;

    public BillItem() {
        super();
        double height = 29;
        double width = 311;
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.setPadding(new Insets(2,1,2,1));

        Button editButton = new Button();
        Image image = new Image("file:./src/main/resources/image/editing.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(19);
        imageView.setFitWidth(19);
        editButton.setGraphic(imageView);

        Label itemLabel = new Label("Barang 1");
        Label quantityLabel = new Label("Qty: 3 x USD 100");
        quantityLabel.setPrefWidth(140);
        quantityLabel.setStyle("-fx-font-size: 10");
        Label priceLabel = new Label("USD 3000000");
        priceLabel.setStyle("-fx-font-size: 11");

        VBox vBox = new VBox(4);
        vBox.getChildren().addAll(itemLabel, quantityLabel);
        vBox.setPrefWidth(140);
        vBox.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(vBox, priceLabel, editButton);
        priceLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(priceLabel, new Insets(height / 2,0,height / 2,0));
        HBox.setMargin(editButton, new Insets(height / 3,0,height / 2,35));
    }
}
