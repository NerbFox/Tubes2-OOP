package org.posapp.view.manajemen_barang;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.posapp.view.custom_components.FixedSizeSearchBar;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManajemenBarangView extends Pane {
    private TextField searchField;
    private FixedSizeTable<ItemBarang>  table;
    HBox layout;
    List<ItemBarang> items = new ArrayList<ItemBarang>(Arrays.asList(
            new ItemBarang("rokok", "obat", 10, 1.3,3.3),
            new ItemBarang("teh", "minuman", 11, 1.3,3.3),
            new ItemBarang("kopi", "minuman", 16, 1.3,3.3),
            new ItemBarang("tempe", "makanan", 0, 1.3,3.3)
    ));

    public ManajemenBarangView(String nama) {
        layout = new HBox();
        this.setStyle("-fx-background-color: #0000FF;");

        VBox leftSideLayout = new VBox();

        Pane searchPane = new Pane();
        searchField = new FixedSizeSearchBar(540, 20, "Cari Barang ... ", ((oldValue, newValue) -> searchHandler(newValue)));
        searchPane.getChildren().add(searchField);
        searchPane.setPadding(new Insets(0, 0, 20, 0));

        String[] headers = new String[] {"Nama", "Kategori", "Stok", "HargaJual", "HargaBeli"};
        table = new FixedSizeTable<ItemBarang>(520, 540, headers, items.toArray(new ItemBarang[0]), this::onRowSelect);

        Pane btnPane = new Pane();
        Button addBarangButton = new Button("+");
        addBarangButton.setMinWidth(540);
        addBarangButton.setMaxHeight(20);
        addBarangButton.setOnAction(event -> add());
        btnPane.getChildren().add(addBarangButton);
        btnPane.setPadding(new Insets(0, 0, 0, 0));

        leftSideLayout.getChildren().addAll(searchPane, table, btnPane);
        leftSideLayout.setPadding(new Insets(20, 20, 20, 20));

        VBox test = detailBarang(new ItemBarang("tempe", "makanan", 0, 1.3,3.3));
        layout.getChildren().addAll(leftSideLayout, test);
        this.getChildren().add(layout);
    }

//    private TableView<ItemBarang> tableListBarang(){
//        TableView<ItemBarang> tableView = new TableView<>();
//
//        TableColumn<ItemBarang, String> nameCol = new TableColumn<>("Nama");
//        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//
//        TableColumn<ItemBarang, String> catCol = new TableColumn<>("Kategori");
//        catCol.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
//
//        TableColumn<ItemBarang, Integer> qtyCol = new TableColumn<>("Stok");
//        qtyCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
//
//        tableView.setItems(items);
//        tableView.getColumns().addAll(nameCol, catCol, qtyCol);
//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        tableView.setPrefSize(540, 540);
//
//        tableView.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2){
//                ItemBarang selectItem = tableView.getSelectionModel().getSelectedItem();
//                if (selectItem != null){
//                    System.out.println("Clicked");
//                    layout.getChildren().set(1, detailBarang(selectItem));
//
//                }
//            }
//        });
//
//        return tableView;
//    }

    private void onRowSelect(ItemBarang selectedItem){
        layout.getChildren().set(1, detailBarang(selectedItem));
    }
    private void searchHandler(String input) {
        if (input.isEmpty()){
            table.setItems(FXCollections.observableArrayList(items));
            return;
        }

        ObservableList<ItemBarang> searchResult = FXCollections.observableArrayList();
        for (ItemBarang item : items){
            if (item.getNama().toLowerCase().contains(input.toLowerCase())) {
                searchResult.add(item);
            }
        }
        table.setItems(searchResult);
    }

    private void add(){
        items.add(new ItemBarang("New Barang", "Kategori", 11, 1.2, 7.8));
        table.setItems(FXCollections.observableArrayList(items));
    }

    private void edit(ItemBarang item, String nama, String kat, Integer qty) {
        items.remove(item);
        item.setNama(nama);
        item.setKategori(kat);
        item.setStok(qty);
        items.add(item);
        table.setItems(FXCollections.observableArrayList(items));
    }
    private VBox detailBarang(ItemBarang item){
        VBox detailView = new VBox();

        Label namaLabel = new Label("Nama");
        TextField namaField = new TextField();
        namaField.setText(item.getNama());

        Label katLabel = new Label("Kategori");
        TextField katField = new TextField();
        katField.setText(item.getKategori());

        Label qtyLabel = new Label("Quantity");
        TextField qtyField = new TextField();
        qtyField.setText(item.getStok().toString());

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
