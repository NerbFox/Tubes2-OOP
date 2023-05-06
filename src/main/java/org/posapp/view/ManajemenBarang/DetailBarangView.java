package org.posapp.view.ManajemenBarang;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.posapp.model.Barang;
import javafx.scene.image.Image;

import java.awt.*;

public class DetailBarangView extends VBox{
    public DetailBarangView(Barang item, Pane view) {
//        setStyle("-fx-background-color: #D9D9D9");
        Image image = new Image("https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?resize=476%2C280&ssl=1", 150, 150, true, true);
//        Image image = new Image("file:./src/main/resources/image/logo_itb.png");
        ImageView imageView = new ImageView((image));

        Label namaLabel = new Label("Nama");
        TextField namaField = new TextField();
        namaField.setText("Nama ... ");
        namaLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label katLabel = new Label("Kategori");
        TextField katField = new TextField();
        katField.setText("Kategori ... ");
        katLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label stokLabel = new Label("Stok");
        TextField stokField = new TextField();
        stokField.setText("Stok ... ");
        stokLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label beliLabel = new Label("Harga Beli");
        TextField beliField = new TextField();
        beliField.setText("Harga Beli ... ");
        beliLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label jualLabel = new Label("Harga Jual");
        TextField jualField = new TextField();
        jualField.setText("Harga Jual ... ");
        jualLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");


        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-font-size: 20px; -fx-font-weight: normal;");
        Button btnDelete = new Button("Delete");
        btnDelete.setStyle("-fx-font-size: 20px; -fx-font-weight: normal;");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnDelete, btnSave);
        buttons.setAlignment(Pos.CENTER);
        HBox.setMargin(btnDelete, new Insets(0, 159, 0, 0));
        HBox.setMargin(btnSave, new Insets(0, 0, 0, 149));
        setMargin(buttons, new Insets(10, 0, 0, 0));

        setAlignment(Pos.CENTER);
        setMargin(namaLabel, new Insets(0,400,0,0));
        setMargin(katLabel, new Insets(10,380,0,0));
        setMargin(stokLabel, new Insets(10,410,0,0));
        setMargin(beliLabel, new Insets(10,370,0,0));
        setMargin(jualLabel, new Insets(10,370,0,0));

        setPrefSize(420, 450);
        getChildren().addAll(imageView, namaLabel, namaField, katLabel, katField, stokLabel, stokField, beliLabel, beliField, jualLabel, jualField, buttons);
    }
}
