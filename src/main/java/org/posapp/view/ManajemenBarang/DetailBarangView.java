package org.posapp.view.ManajemenBarang;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import org.posapp.controller.command.DeleteBarangCommand;
import org.posapp.controller.command.SaveBarangCommand;
import org.posapp.model.Barang;
import javafx.scene.image.Image;

import java.util.Stack;
import java.util.function.UnaryOperator;

import static java.util.Arrays.copyOfRange;

@Getter
public class DetailBarangView extends VBox{
    private TextField namaField;
    private TextField katField;
    private TextField stokField;
    private TextField beliField;
    private TextField jualField;
    private Barang item;
    private Button btnImageInput;
    private Label pathLabel;
    private String selectedFile;

    public DetailBarangView(Barang _item, ManajemenBarangView view) {
//        Image image = new Image("https://i0.wp.com/www.printmag.com/wp-content/uploads/2021/02/4cbe8d_f1ed2800a49649848102c68fc5a66e53mv2.gif?resize=476%2C280&ssl=1", 150, 150, true, true);
        item = _item;
//        if (_item.getPathGambar().equals("url")) {
//            selectedFile = "./src/main/resources/image/logo_itb.png";
//        } else {
//            selectedFile = _item.getPathGambar();
////            System.out.println(selectedFile);
//        }
        selectedFile = _item.getPathGambar();
        Image image = new Image(selectedFile, 150, 150, true, true);
        ImageView imageView = new ImageView((image));
        setMargin(imageView, new Insets(20, 0, 0 ,100));

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

                    if (input.matches("[0-9]*")){
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

        Stage stage = new Stage();
        btnImageInput = new Button("Pilih Gambar");
        btnImageInput.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pilih Gambar Barang");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedFile = fileChooser.showOpenDialog(stage).toURI().toString();
            if (selectedFile != null) {
//                selectedFile = selectedFile.substring(5);
//                System.out.println(selectedFile);
                imageView.setImage(new Image(selectedFile, 150, 150, true, true));
                pathLabel.setText(selectedFile);
            }
        });
        pathLabel = new Label(item.getPathGambar());
        pathLabel.setMaxWidth(280);
        pathLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: normal;");

        HBox selectGambarPane = new HBox();
        selectGambarPane.getChildren().addAll(btnImageInput, pathLabel);
        HBox.setMargin(btnImageInput, new Insets(10, 0, 0, 0));
        HBox.setMargin(pathLabel, new Insets(15, 0, 0, 20));

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
        HBox.setMargin(btnDelete, new Insets(0, 110, 0, 0));
        HBox.setMargin(btnSave, new Insets(0, 0, 0, 80));
        setMargin(buttons, new Insets(10, 0, 0, 0));

//        setAlignment(Pos.CENTER);
        setMargin(namaLabel, new Insets(0,0,0,0));
        setMargin(katLabel, new Insets(10,0,0,0));
        setMargin(stokLabel, new Insets(10,0,0,0));
        setMargin(beliLabel, new Insets(10,0,0,0));
        setMargin(jualLabel, new Insets(10,0,0,0));
        setMargin(pathLabel, new Insets(10,0,0,0));
//
        btnSave.disableProperty().bind(Bindings.isEmpty(namaField.textProperty()).or(Bindings.isEmpty(katField.textProperty())));


        setPadding(new Insets(0, 0, 0,30 ));
        setPrefSize(440, 450);
        getChildren().addAll(imageView, namaLabel, namaField, katLabel, katField, stokLabel, stokField, beliLabel, beliField, jualLabel, jualField, selectGambarPane, buttons);
    }
}
