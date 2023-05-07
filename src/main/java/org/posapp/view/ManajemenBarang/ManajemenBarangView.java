package org.posapp.view.ManajemenBarang;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.posapp.controller.command.AddBarangCommand;
import org.posapp.controller.manajemen_barang.ManajemenBarangController;
import org.posapp.model.Barang;
import org.posapp.view.custom_components.FixedSizeSearchBar;
import org.posapp.view.custom_components.FixedSizeTable;

@Getter @Setter
public class ManajemenBarangView extends Pane {
    ManajemenBarangController controller;
    private TextField searchField;
    VBox leftSideLayout;
    VBox rightSideLayout;
    private FixedSizeTable<Barang>  table;
    HBox layout;

    public ManajemenBarangView(String nama) {
        controller = new ManajemenBarangController(this);

        layout = new HBox();
        this.setStyle("-fx-background-color: #ffffff;");

        leftSideLayout = new VBox();

        Pane searchPane = new Pane();
        searchField = new FixedSizeSearchBar(540, 20, "Cari Barang ... ", ((oldValue, newValue) -> searchHandler(newValue)));
        searchPane.getChildren().add(searchField);
        searchPane.setPadding(new Insets(0, 0, 20, 0));

        String[] headers = new String[] {"Nama", "Kategori", "Stok", "HargaJual", "HargaBeli"};
        table = new FixedSizeTable<Barang>(520, 540, headers, headers, controller.getModel().getArrBarang().toArray(new Barang[0]), this::onRowSelect);

        Pane btnPane = new Pane();
        Button addBarangButton = new Button("+");
        addBarangButton.setMinWidth(540);
        addBarangButton.setMaxHeight(20);
        AddBarangCommand addBarangCommand = new AddBarangCommand(this);
        addBarangButton.setOnAction(e -> addBarangCommand.execute());
        btnPane.getChildren().add(addBarangButton);
        btnPane.setPadding(new Insets(0, 0, 0, 0));

        leftSideLayout.getChildren().addAll(searchPane, table, btnPane);
        leftSideLayout.setPadding(new Insets(20, 20, 20, 20));

        rightSideLayout = new DetailBarangView (new Barang(-1, "", "", 0, 0,0, "url"), this);

        layout.getChildren().addAll(leftSideLayout, rightSideLayout);
        this.getChildren().add(layout);
    }

    private void onRowSelect(Barang selectedItem){
        layout.getChildren().set(1, new DetailBarangView(selectedItem, this));
    }
    private void searchHandler(String input) {
        if (input.isEmpty()){
            table.setItems(FXCollections.observableArrayList(controller.getModel().getArrBarang()));
            return;
        }

        ObservableList<Barang> searchResult = FXCollections.observableArrayList();
        for (Barang item : controller.getModel().getArrBarang()){
            if (item.getNama().toLowerCase().contains(input.toLowerCase())) {
                searchResult.add(item);
            }
        }
        table.setItems(searchResult);
    }

}
