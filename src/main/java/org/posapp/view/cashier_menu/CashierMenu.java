package org.posapp.view.cashier_menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.posapp.model.Barang;
import org.posapp.model.Customer;
import org.posapp.model.Member;
import org.posapp.model.datastore.Datastore;
import org.posapp.view.custom_components.FixedSizeSearchBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class CashierMenu extends GridPane {

    ObservableList<String> customerNames;
    ObservableList<String> categoryNames;
    private CashierItems tableCasItems;

    private ArrayList<Barang> tmpListBarang = Datastore.getInstance().getArrBarang();
    private ArrayList<Customer> customers = Datastore.getInstance().getArrCustomer();

    public CashierMenu() {
        super();
        this.setPadding(new Insets(20,20,20,20));

        GridPane gridLeft = new GridPane();
        GridPane gridRight = new GridPane();

        TextField maxPrice = new FixedSizeSearchBar(143,27,"Maximum Price", ((oldValue, newValue) -> maximumPriceHandler(newValue, gridLeft)));
        maxPrice.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
                change -> {
                    String input = change.getText();

                    if (input.matches("[0-9]*\\.?[0-9]*")){
                        return change;
                    } else {
                        return null;
                    }
                }
        ));
//        maxPrice.setPromptText("Maximum Price");
//        maxPrice.setPrefHeight(27);
//        maxPrice.setPrefWidth(143);


        TextField minPrice = new FixedSizeSearchBar(143,27,"Minimum Price", ((oldValue, newValue) -> minimumPriceHandler(newValue, gridLeft)));
        minPrice.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
                change -> {
                    String input = change.getText();

                    if (input.matches("[0-9]*\\.?[0-9]*")){
                        return change;
                    } else {
                        return null;
                    }
                }
        ));
//        minPrice.setPromptText("Minimum Price");
//        minPrice.setPrefHeight(27);
//        minPrice.setPrefWidth(143);

//        FixedSizeSearchBar searchBar = new FixedSizeSearchBar(649, 27, "Search Items Here", )
        TextField searchBar = new FixedSizeSearchBar(649,27,"Search Items Here",((oldValue, newValue) -> searchItemHandler(newValue,gridLeft)));
//        searchBar.setPromptText("Search Items Here");
//        searchBar.setPrefHeight(27);
//        searchBar.setPrefWidth(649);

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Barang barang : tmpListBarang) {
            if (!items.contains(barang.getKategori())) {
                items.add(barang.getKategori());
            }
        }


        ObservableList<String> namaCustomers = FXCollections.observableArrayList();
        for (Customer cus : customers) {
            if (cus instanceof Member) {
                if (!namaCustomers.contains(cus.getIdCust())) {
                    namaCustomers.add(((Member) cus).getName());
                }
            }
        }
        System.out.println(namaCustomers);

        ComboBox<String> categoryDropdown = new ComboBox<>(items);
        categoryDropdown.setPrefHeight(27);
        categoryDropdown.setPrefWidth(212);
        categoryDropdown.setPromptText("Select Category");
        categoryDropdown.setEditable(true);
        categoryDropdown.setOnAction(e -> {
            categorySelectedHandler(categoryDropdown.getSelectionModel().getSelectedItem(), gridLeft);
        });

        ComboBox<String> customerDropdown = new ComboBox<>(namaCustomers);
        customerDropdown.setPrefWidth(189);
        customerDropdown.setPrefHeight(27);
        customerDropdown.setPromptText("Select Customer");
        customerDropdown.setEditable(true);

        Button selectBill = new Button("Select Bill");
        selectBill.setPrefHeight(27);
        selectBill.setPrefWidth(107);

        Button saveBill = new Button("Save Bill");
        saveBill.setPrefHeight(27);
        saveBill.setPrefWidth(107);

        Button clearBill = new Button("Clear Bill");
        clearBill.setPrefHeight(27);
        clearBill.setPrefWidth(107);

        Button checkout = new Button("Checkout");
        checkout.setPrefWidth(201);
        checkout.setPrefHeight(33);
        GridPane.setHalignment(checkout, HPos.CENTER);


        // Filter search grid
        GridPane filterSearch = new GridPane();
        filterSearch.add(categoryDropdown, 0, 0);
        filterSearch.add(minPrice, 1, 0);
        filterSearch.add(maxPrice, 2, 0);
        filterSearch.setHgap(75);
        GridPane.setHalignment(maxPrice, HPos.RIGHT);
        GridPane.setHalignment(minPrice, HPos.RIGHT);

        // Clear and save grid
        GridPane saveClear = new GridPane();
        saveClear.add(saveBill, 0, 0);
        saveClear.add(clearBill, 1, 0);
        GridPane.setHalignment(saveClear, HPos.CENTER);
        saveClear.setAlignment(Pos.CENTER);
        saveClear.setHgap(40);

        // Dropdown customer and select bill grid
        GridPane customerSelectAndBill = new GridPane();
        customerSelectAndBill.add(customerDropdown, 0, 0);
        customerSelectAndBill.add(selectBill, 1, 0);
        GridPane.setHalignment(customerSelectAndBill, HPos.CENTER);
        customerSelectAndBill.setAlignment(Pos.CENTER);
        customerSelectAndBill.setHgap(40);

        // Left grid for selecting items
//        GridPane gridLeft = new GridPane();
        gridLeft.setVgap(25);
        gridLeft.add(searchBar, 0, 0);
        gridLeft.add(filterSearch, 0, 1);
        tableCasItems = new CashierItems(tmpListBarang);
        gridLeft.add(tableCasItems, 0, 2);

        // Right grid for selecting bill and checkout
//        GridPane gridRight = new GridPane();
        gridRight.setPadding(new Insets(50,0,0,0));
        gridRight.setVgap(25);
        gridRight.add(customerSelectAndBill, 0, 0);
        gridRight.add(new BillItems(), 0, 1);
        gridRight.add(saveClear, 0, 2);
        gridRight.add(checkout, 0, 3);

        this.setHgap(30);
        this.setVgap(15);
        this.add(gridLeft, 0, 0);
        this.add(gridRight, 1, 0);
    }

    private void categorySelectedHandler(String input, GridPane gridLeft) {
        if (input.isEmpty()){
            tableCasItems = new CashierItems(tmpListBarang);
        } else {
            ObservableList<Barang> searchResult = FXCollections.observableArrayList();
            for (Barang item : tmpListBarang){
                if (item.getKategori().contains(input)) {
                    searchResult.add(item);
                }
            }
            ArrayList<Barang> arrayListResult = new ArrayList<>(searchResult);
            Node node = gridLeft.getChildren().get(2);
            gridLeft.getChildren().remove(node);
            tableCasItems = new CashierItems(arrayListResult);
        }
        gridLeft.add(tableCasItems,0,2);
    }

    private void minimumPriceHandler(String input, GridPane gridLeft) {
        if (input.isEmpty()){
            tableCasItems = new CashierItems(tmpListBarang);
        } else {
            ObservableList<Barang> searchResult = FXCollections.observableArrayList();
            for (Barang item : tableCasItems.getContentBarang()){
                if (item.getHargaJual() >= Integer.parseInt(input)) {
                    searchResult.add(item);
                }
            }
            ArrayList<Barang> arrayListResult = new ArrayList<>(searchResult);
            Node node = gridLeft.getChildren().get(2);
            gridLeft.getChildren().remove(node);
            tableCasItems = new CashierItems(arrayListResult);
        }
        gridLeft.add(tableCasItems,0,2);
    }

    private void maximumPriceHandler(String input, GridPane gridLeft) {
        if (input.isEmpty()){
            tableCasItems = new CashierItems(tmpListBarang);
        } else {
            ObservableList<Barang> searchResult = FXCollections.observableArrayList();
            for (Barang item : tableCasItems.getContentBarang()){
                if (item.getHargaJual() <= Integer.parseInt(input)) {
                    searchResult.add(item);
                }
            }
            ArrayList<Barang> arrayListResult = new ArrayList<>(searchResult);
            Node node = gridLeft.getChildren().get(2);
            gridLeft.getChildren().remove(node);
            tableCasItems = new CashierItems(arrayListResult);
        }
        gridLeft.add(tableCasItems,0,2);
    }

    private void searchItemHandler(String input, GridPane gridLeft) {
        if (input.isEmpty()){
            tableCasItems = new CashierItems(tmpListBarang);
        } else {
            ObservableList<Barang> searchResult = FXCollections.observableArrayList();
            for (Barang item : tableCasItems.getContentBarang()){
                if (item.getNama().toLowerCase().contains(input)) {
                    searchResult.add(item);
                }
            }
            ArrayList<Barang> arrayListResult = new ArrayList<>(searchResult);
            Node node = gridLeft.getChildren().get(2);
            gridLeft.getChildren().remove(node);
            tableCasItems = new CashierItems(arrayListResult);
        }
        gridLeft.add(tableCasItems,0,2);
    }
}

//    private void searchHandler(String input) {
//        if (input.isEmpty()){
//            table.setItems(FXCollections.observableArrayList(items));
//            return;
//        }
//
//        ObservableList<Barang> searchResult = FXCollections.observableArrayList();
//        for (Barang item : items){
//            if (item.getNama().toLowerCase().contains(input.toLowerCase())) {
//                searchResult.add(item);
//            }
//        }
//        table.setItems(searchResult);
//    }

