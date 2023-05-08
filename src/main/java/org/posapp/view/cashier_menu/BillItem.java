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

import java.util.HashMap;
import java.util.function.Consumer;

public class BillItem extends HBox {
    Barang barang;

    public BillItem(Barang barang, Integer quantity, Consumer<Barang> onDelete) {
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
        editButton.setOnAction(event -> onDelete.accept(barang));


        Label itemLabel = new Label(barang.getNama());
        Label quantityLabel = new Label("Qty: " + quantity + " - " + barang.getHargaJual());
        quantityLabel.setPrefWidth(140);
        quantityLabel.setStyle("-fx-font-size: 10");
        Label priceLabel = new Label("USD " + (quantity* barang.getHargaJual()));
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
