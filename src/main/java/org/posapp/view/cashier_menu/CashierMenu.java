package org.posapp.view.cashier_menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.posapp.model.Barang;
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

    List<Barang> tmpListBarang = new ArrayList<Barang>(Arrays.asList(
            new Barang(1, "rokok", "obat", 10, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(2, "teh", "minuman", 11, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(3, "kopi", "minuman", 16, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(4, "tempe", "makanan", 0, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(5, "jagung", "minuman", 32, 100000,3, "file:./src/main/resources/image/50.png"),
            new Barang(6, "yasudha", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(7, "sdfewrg", "minuman", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(8, "agtevbd", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(9, "hfjd", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(10, "kjntjgnwe", "narkoba", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(11, "habchbe", "makanan", 4, 4,3, "file:./src/main/resources/image/50.png"),
            new Barang(12, "sabfuie", "CDJAV", 1999, 1000000,3, "file:./src/main/resources/image/50.png"),
            new Barang(13, "khagf", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(14, "uerhgv", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(15, "dncbh", "CDJAV", 349, 1,10000000, "file:./src/main/resources/image/50.png"),
            new Barang(16, "mnxcjveui", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(17, "qhuqw", "CDJAV++", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(18, "ndbdbewf", "makanan", 4, 1,3, "file:./src/main/resources/image/50.png"),
            new Barang(19, "ahgsda", "++++", 4, 1,3, "file:./src/main/resources/image/50.png")
    ));

    public CashierMenu() {
        super();
        this.setPadding(new Insets(20,20,20,20));

        TextField maxPrice = new FixedSizeSearchBar(143,27,"Maximum Price", ((oldValue, newValue) -> maximumPriceHandler(newValue)));
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


        TextField minPrice = new FixedSizeSearchBar(143,27,"Minimum Price", ((oldValue, newValue) -> minimumPriceHandler(newValue)));
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
        TextField searchBar = new FixedSizeSearchBar(649,27,"Search Items Here",((oldValue, newValue) -> searchItemHandler(newValue)));
//        searchBar.setPromptText("Search Items Here");
//        searchBar.setPrefHeight(27);
//        searchBar.setPrefWidth(649);

        ObservableList<String> items = FXCollections.observableArrayList(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4",
                "Option 5"
        );
        ObservableList<String> customers = FXCollections.observableArrayList(
                "Customer 1",
                "Customer 2",
                "Customer 3",
                "Customer 4",
                "Customer 5"
        );
        ComboBox<String> categoryDropdown = new ComboBox<>(items);
        categoryDropdown.setPrefHeight(27);
        categoryDropdown.setPrefWidth(212);
        categoryDropdown.setPromptText("Select Category");
        categoryDropdown.setEditable(true);

        ComboBox<String> customerDropdown = new ComboBox<>(customers);
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
        GridPane gridLeft = new GridPane();
        gridLeft.setVgap(25);
        gridLeft.add(searchBar, 0, 0);
        gridLeft.add(filterSearch, 0, 1);
        tableCasItems = new CashierItems(tmpListBarang);
        gridLeft.add(tableCasItems, 0, 2);

        // Right grid for selecting bill and checkout
        GridPane gridRight = new GridPane();
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

    private void minimumPriceHandler(String newValue) {
    }

    private void maximumPriceHandler(String newValue) {
    }

    private void searchItemHandler(String newValue) {

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
}
