package org.posapp.view.cashier_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.posapp.model.Barang;

import java.util.function.Consumer;

public class CashierItem extends HBox {
    private Barang tmpBarang;


    // dummy

    CashierItem(Barang tmpBarang, Consumer<Barang> onButtonPressed) {
        super(10);
//        tmpBarang = new Barang(1, "rokok", "obat", 10, 1,3, "file:./src/main/resources/image/50.png");

        Label itemLabel = new Label(tmpBarang.getNama());
        itemLabel.setStyle(" -fx-font-size: 14");
        Label categoryLabel = new Label(tmpBarang.getKategori());
        Label stockLabel = new Label("Stok: " +Integer.toString(tmpBarang.getStok()));
        Label priceLabel = new Label(Float.toString(tmpBarang.getHargaJual()));

        double height = 52;
        double width = 632;
        this.setPrefHeight(height);
        this.setPrefWidth(width);

        Button imgButtonSelect = new Button();
        imgButtonSelect.setOnAction(event -> onButtonPressed.accept(tmpBarang));
        Image image = new Image(tmpBarang.getPathGambar());
        ImageView imageView = new ImageView(image);
        imgButtonSelect.setGraphic(imageView);
//        HBox.setMargin(imgButtonSelect, new Insets(5,5,5,5));

        GridPane itemDetails = new GridPane();

        itemDetails.setHgap(100);
        itemDetails.setVgap(15);
        itemDetails.add(itemLabel, 0, 0);
        itemDetails.add(categoryLabel, 0 ,1);
        itemDetails.add(stockLabel, 1, 1);

        itemLabel.setPadding(new Insets(5,0,0,0));
        categoryLabel.setPadding(new Insets(0,0,0,0));
        stockLabel.setPadding(new Insets(0,0,0,0));

        this.getChildren().addAll(imgButtonSelect, itemDetails, priceLabel);
        priceLabel.setAlignment(Pos.CENTER);

        HBox.setMargin(priceLabel, new Insets(height / 2,0,height / 2,230));
    }
}
