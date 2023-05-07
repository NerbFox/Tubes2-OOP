package org.posapp.view.History;

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
import java.util.function.UnaryOperator;

public class HistoryView extends Pane {
    private TextField searchID;
    private Button btnDate;
    private FixedSizeTable<TranHis> table1;
    VBox leftSideLayout;
    VBox rightSideLayout;
    HBox layout;

    List<Barang> items1 = new ArrayList<Barang>(Arrays.asList(
            new Barang(1, "rokok", "obat", 10, 1,3, "url"),
            new Barang(2, "teh", "minuman", 11, 1,3, "url"),
            new Barang(3, "kopi", "minuman", 16, 1,3, "url"),
            new Barang(4, "tempe", "makanan", 0, 1,3, "url")
    ));

    List<Barang> items2 = new ArrayList<Barang>(Arrays.asList(
            new Barang(1, "rokok", "obat", 10, 1,3, "url"),
            new Barang(2, "teh", "minuman", 11, 1,3, "url"),
            new Barang(3, "kopi", "minuman", 16, 1,3, "url"),
            new Barang(4, "tempe", "makanan", 0, 1,3, "url"),
            new Barang(4, "jagung", "minuman", 32, 100000,3, "url"),
            new Barang(4, "yasudha", "makanan", 4, 1,3, "url")
    ));

    List<TranHis> itemstesttable1 = new ArrayList<TranHis>(Arrays.asList(
            new TranHis(1,"2023-01-1419:55;34", "12123200",1,"Hanan","Member", "082222222",items1),
            new TranHis(2,"2023-01-14\n19:55;34", "121200",2,"joko","VIP", "082222222",items1),
            new TranHis(3,"2023-01-14\n19:55;34", "12123200",3,"wi","VIP", "0822222223",items2),
            new TranHis(4,"2023-01-14\n19:55;34", "121200",4,"yoman","Member", "082222222",items2)
    ));

    TranHis itemsdefault = new TranHis(4,"2023-01-14\n19:55;34", "121200",4,"yoman","Member", "082222222",items2);

    public HistoryView(String nama){
        layout = new HBox();
        this.setStyle("-fx-background-color: #ffffff;");

        leftSideLayout = new VBox();

        Pane searchIDPane = new Pane();
        searchID = new FixedSizeSearchBar(340, 20, "Search by ID", ((oldValue, newValue) -> searchHandler(newValue)));
        searchID.setTextFormatter(new TextFormatter<String>((UnaryOperator<TextFormatter.Change>)
                change -> {
                    String input = change.getText();

                    if (input.matches("[0-9]*")){
                        return change;
                    } else {
                        return null;
                    }
                }
        ));
        searchIDPane.getChildren().add(searchID);
        searchIDPane.setPadding(new Insets(0, 0, 20, 10));

        Pane btnDatePane = new Pane();
        btnDate = new Button("Choose by Date");
        btnDate.setMinWidth(340);
        btnDate.setMaxHeight(20);
        btnDate.setOnAction(event -> getDate());
        btnDatePane.getChildren().add(btnDate);
        btnDatePane.setPadding(new Insets(0, 0, 20, 0));

        Label hislab = new Label(("Transaction History"));
        hislab.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        String[] headers = new String[] {"BillID", "Date", "TotalBill"};
        table1 = new FixedSizeTable<TranHis>(470, 440, headers, headers, itemstesttable1.toArray(new TranHis[0]), this::onRowSelect);

        leftSideLayout.getChildren().addAll(searchIDPane, btnDatePane, hislab, table1);
        leftSideLayout.setPadding(new Insets(20, 20, 20, 20));

        rightSideLayout = new DetailHistoryView(itemsdefault);
        layout.getChildren().addAll(leftSideLayout, rightSideLayout);
        this.getChildren().add(layout);
    }

    private void searchHandler(String input) {
        if (input.isEmpty()){
            table1.setItems(FXCollections.observableArrayList(itemstesttable1));
            return;
        }

        ObservableList<TranHis> searchResult = FXCollections.observableArrayList();
        for (TranHis item : itemstesttable1){
            if (item.getID().equals(Integer.parseInt(input))) {
                searchResult.add(item);
            }
        }
        table1.setItems(searchResult);
    }

    private void getDate(){

    }

    private void onRowSelect(TranHis selectedItem){
        layout.getChildren().set(1, new DetailHistoryView(selectedItem));
    }
}
