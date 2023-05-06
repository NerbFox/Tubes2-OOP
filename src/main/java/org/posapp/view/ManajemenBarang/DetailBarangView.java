package org.posapp.view.ManajemenBarang;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.posapp.controller.manajemen_barang.DeleteBarangCommand;
import org.posapp.controller.manajemen_barang.SaveBarangCommand;
import org.posapp.model.Barang;
import javafx.scene.image.Image;

import java.util.function.UnaryOperator;

@Getter
public class DetailBarangView extends VBox{
    private TextField namaField;
    private TextField katField;
    private TextField stokField;
    private TextField beliField;
    private TextField jualField;
    private Barang item;
//    private TextField pathField;

    public DetailBarangView(Barang _item, ManajemenBarangView view) {
//        Image image = new Image("https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?resize=476%2C280&ssl=1", 150, 150, true, true);
        item = _item;
        Image image = new Image("file:./src/main/resources/image/logo_itb.png", 150, 150, true, true);
        ImageView imageView = new ImageView((image));

        Label namaLabel = new Label("Nama");
        namaField = new TextField();
        namaField.setText(item.getNama());
        namaLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label katLabel = new Label("Kategori");
        katField = new TextField();
        katField.setText(item.getKategori());
        katLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label stokLabel = new Label("Stok");
        stokField = new TextField();
        stokField.setText(String.valueOf(item.getStok()));
        stokField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
                change -> {
                    String input = change.getText();

                    if (input.matches("[0-9]*\\.?[0-9]*")){
                        return change;
                    } else {
                        return null;
                    }
                }
        ));
        stokLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label beliLabel = new Label("Harga Beli");
        beliField = new TextField();
        beliField.setText(String.valueOf(item.getHargaBeli()));
        beliField .setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
                change -> {
                    String input = change.getText();

                    if (input.matches("[0-9]*\\.?[0-9]*")){
                        return change;
                    } else {
                        return null;
                    }
                }
        ));
        beliLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");

        Label jualLabel = new Label("Harga Jual");
        jualField = new TextField();
        jualField.setText(String.valueOf(item.getHargaJual()));
        jualField.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
            change -> {
                String input = change.getText();

                if (input.matches("[0-9]*\\.?[0-9]*")){
                    return change;
                } else {
                    return null;
                }
            }
        ));
        jualLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal;");


        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-font-size: 20px; -fx-font-weight: normal;");
        SaveBarangCommand saveBarangCommand = new SaveBarangCommand(view);
        btnSave.setOnAction(e -> saveBarangCommand.execute());

        Button btnDelete = new Button("Delete");
        DeleteBarangCommand deleteBarangCommand = new DeleteBarangCommand(view);
        btnDelete.setOnAction(e -> deleteBarangCommand.execute());
        btnDelete.setStyle("-fx-font-size: 20px; -fx-font-weight: normal;");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnDelete, btnSave);
        buttons.setAlignment(Pos.CENTER);
        HBox.setMargin(btnDelete, new Insets(0, 130, 0, 0));
        HBox.setMargin(btnSave, new Insets(0, 0, 0, 110));
        setMargin(buttons, new Insets(10, 0, 0, 0));

        setAlignment(Pos.CENTER);
        setMargin(namaLabel, new Insets(0,360,0,0));
        setMargin(katLabel, new Insets(10,340,0,0));
        setMargin(stokLabel, new Insets(10,370,0,0));
        setMargin(beliLabel, new Insets(10,320,0,0));
        setMargin(jualLabel, new Insets(10,320,0,0));

        setPadding(new Insets(0, 0, 0,30 ));
        setPrefSize(440, 450);
        getChildren().addAll(imageView, namaLabel, namaField, katLabel, katField, stokLabel, stokField, beliLabel, beliField, jualLabel, jualField, buttons);
    }
}
