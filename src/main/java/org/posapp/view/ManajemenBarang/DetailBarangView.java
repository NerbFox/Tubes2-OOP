package org.posapp.view.ManajemenBarang;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.posapp.model.Barang;
import javafx.scene.image.Image;

import java.awt.*;

public class DetailBarangView extends VBox{
    public DetailBarangView(Barang item, Pane view) {
        Image image = new Image("file:./src/main/resources/image/logo_itb.png");
        ImageView imageView = new ImageView((image));
//        imageView.re

        Label namaLabel = new Label("Nama");
        TextField namaField = new TextField();
        namaField.setText("Nama ... ");

        Label katLabel = new Label("Kategori");
        TextField katField = new TextField();
        katField.setText("Kategori ... ");

        Label stokLabel = new Label("Stok");
        TextField stokField = new TextField();
        stokField.setText("Stok ... ");

        Button btnSave = new Button("Save");
        Button btnDelete = new Button("Delete");
//        btnSave.setOnAction((event) -> edit(item, namaField.getText(), katField.getText(), Integer.parseInt(qtyField.getText())));

        getChildren().addAll(imageView, namaLabel, namaField, katLabel, katField, stokLabel, stokField, btnSave, btnDelete);

    }
}
