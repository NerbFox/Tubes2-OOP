package org.posapp.view.cashier_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CashierItem extends HBox {

    CashierItem() {
        super(10);
        Label itemLabel = new Label("Barang 1");
        itemLabel.setStyle(" -fx-font-size: 14");
        Label categoryLabel = new Label("Kategori");
        Label stockLabel = new Label("Stok");
        Label priceLabel = new Label("USD 100");

        double height = 52;
        double width = 632;
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        Image image = new Image("file:/home/hanifmz07/oop/Tubes2-OOP/src/main/resources/50.png");
        ImageView imageView = new ImageView(image);
        HBox.setMargin(imageView, new Insets(5,5,5,5));

        GridPane itemDetails = new GridPane();

        itemDetails.setHgap(100);
        itemDetails.setVgap(15);
        itemDetails.add(itemLabel, 0, 0);
        itemDetails.add(categoryLabel, 0 ,1);
        itemDetails.add(stockLabel, 1, 1);

        itemLabel.setPadding(new Insets(5,0,0,0));
        categoryLabel.setPadding(new Insets(0,0,0,0));
        stockLabel.setPadding(new Insets(0,0,0,0));

        this.getChildren().addAll(imageView, itemDetails, priceLabel);
        priceLabel.setAlignment(Pos.CENTER);

        HBox.setMargin(priceLabel, new Insets(height / 2,0,height / 2,230));
    }
}
