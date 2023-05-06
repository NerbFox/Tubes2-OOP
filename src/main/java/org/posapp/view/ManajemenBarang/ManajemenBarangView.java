package org.posapp.view.ManajemenBarang;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.posapp.model.Barang;
import org.posapp.view.custom_components.FixedSizeSearchBar;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManajemenBarangView extends Pane {
    private TextField searchField;
    VBox leftSideLayout;
    Pane rightSideLayout;
    private FixedSizeTable<Barang>  table;
    HBox layout;
    List<Barang> items = new ArrayList<Barang>(Arrays.asList(
            new Barang(1, "rokok", "obat", 10, 1,3, "url"),
            new Barang(2, "teh", "minuman", 11, 1,3, "url"),
            new Barang(3, "kopi", "minuman", 16, 1,3, "url"),
            new Barang(4, "tempe", "makanan", 0, 1,3, "url")
    ));

    public ManajemenBarangView(String nama) {
        layout = new HBox();
        this.setStyle("-fx-background-color: #ffffff;");

        leftSideLayout = new VBox();

        Pane searchPane = new Pane();
        searchField = new FixedSizeSearchBar(540, 20, "Cari Barang ... ", ((oldValue, newValue) -> searchHandler(newValue)));
        searchPane.getChildren().add(searchField);
        searchPane.setPadding(new Insets(0, 0, 20, 0));

        String[] headers = new String[] {"Nama", "Kategori", "Stok", "HargaJual", "HargaBeli"};
        table = new FixedSizeTable<Barang>(520, 540, headers, headers, items.toArray(new Barang[0]), this::onRowSelect);

        Pane btnPane = new Pane();
        Button addBarangButton = new Button("+");
        addBarangButton.setMinWidth(540);
        addBarangButton.setMaxHeight(20);
        addBarangButton.setOnAction(event -> add());
        btnPane.getChildren().add(addBarangButton);
        btnPane.setPadding(new Insets(0, 0, 0, 0));

        leftSideLayout.getChildren().addAll(searchPane, table, btnPane);
        leftSideLayout.setPadding(new Insets(20, 20, 20, 20));

        VBox test = new DetailBarangView (new Barang(4, "tempe", "makanan", 0, 1,3, "url"), this);
        layout.getChildren().addAll(leftSideLayout, test);
        this.getChildren().add(layout);
    }

    private void onRowSelect(Barang selectedItem){
        layout.getChildren().set(1, new DetailBarangView(selectedItem, this));
    }
    private void searchHandler(String input) {
        if (input.isEmpty()){
            table.setItems(FXCollections.observableArrayList(items));
            return;
        }

        ObservableList<Barang> searchResult = FXCollections.observableArrayList();
        for (Barang item : items){
            if (item.getNama().toLowerCase().contains(input.toLowerCase())) {
                searchResult.add(item);
            }
        }
        table.setItems(searchResult);
    }

    private void add(){
        items.add(new Barang(4, "tempe", "makanan", 0, 1,3, "url"));
        table.setItems(FXCollections.observableArrayList(items));
    }

    public void edit(Barang item, String nama, String kat, Integer qty) {
        items.remove(item);
        item.setNama(nama);
        item.setKategori(kat);
        item.setStok(qty);
        items.add(item);
        table.setItems(FXCollections.observableArrayList(items));
    }
    private VBox detailBarang(Barang item){
        VBox detailView = new VBox();

        Label namaLabel = new Label("Nama");
        TextField namaField = new TextField();
        namaField.setText(item.getNama());

        Label katLabel = new Label("Kategori");
        TextField katField = new TextField();
        katField.setText(item.getKategori());

        Label qtyLabel = new Label("Quantity");
        TextField qtyField = new TextField();
        qtyField.setText(String.valueOf(item.getStok()));

        Button btnSave = new Button("Save");
        btnSave.setOnAction((event) -> edit(item, namaField.getText(), katField.getText(), Integer.parseInt(qtyField.getText())));

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction((event) -> {
            items.remove(item);
            table.setItems(FXCollections.observableArrayList(items));
        });

        btnSave.disableProperty().bind(Bindings.isEmpty(namaField.textProperty()).or(Bindings.isEmpty(katField.textProperty())));

        detailView.getChildren().addAll(namaLabel, namaField, katLabel, katField, qtyLabel, qtyField, btnSave, btnDelete);

        return detailView;
    }
}
