package org.posapp.view.History;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.posapp.model.*;

import org.posapp.model.datastore.Datastore;
import org.posapp.view.custom_components.FixedSizeSearchBar;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class HistoryView extends Pane {
    private TextField searchID;
    private Button btnPdf;
    private FixedSizeTable<TranHis> table1;
    VBox leftSideLayout;
    VBox rightSideLayout;
    HBox layout;
    List<TranHis> data;

    public HistoryView(String nama){
        layout = new HBox();
        this.setStyle("-fx-background-color: #ffffff;");

        leftSideLayout = new VBox();

        Pane searchIDPane = new Pane();
        searchID = new FixedSizeSearchBar(440, 20, "Search by ID", ((oldValue, newValue) -> searchHandler(newValue)));
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

        Pane btnPdfPane = new Pane();
        btnPdf = new Button("Download PDF Transaction History");
        btnPdf.setMinWidth(440);
        btnPdf.setMaxHeight(20);
        btnPdf.setOnAction(event -> toPdf());
        btnPdfPane.getChildren().add(btnPdf);
        btnPdfPane.setPadding(new Insets(0, 0, 20, 0));

        Label hislab = new Label(("Transaction History"));
        hislab.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        String[] headers = new String[] {"BillID", "TotalBill"};
        initData();
        table1 = new FixedSizeTable<TranHis>(470, 440, headers, headers, data.toArray(new TranHis[data.size()]), this::onRowSelect);

        leftSideLayout.getChildren().addAll(searchIDPane, btnPdfPane, hislab, table1);
        leftSideLayout.setPadding(new Insets(20, 20, 20, 20));

        rightSideLayout = new DetailHistoryView();
        layout.getChildren().addAll(leftSideLayout, rightSideLayout);
        this.getChildren().add(layout);
    }

    private void toPdf() {
    }

    private void searchHandler(String input) {
        if (input.isEmpty()){
            table1.setItems(FXCollections.observableArrayList(data));
            return;
        }

        ObservableList<TranHis> searchResult = FXCollections.observableArrayList();
        for (TranHis item : data){
            if (item.getID().equals(Integer.parseInt(input))) {
                searchResult.add(item);
            }
        }
        table1.setItems(searchResult);
    }

    private void onRowSelect(TranHis selectedItem){
        layout.getChildren().set(1, new DetailHistoryView(selectedItem));
    }

    private void initData() {
        data = new ArrayList<TranHis>();
        ArrayList<Customer> arrCustomer = Datastore.getInstance().getArrCustomer();

        for (Customer cust :  arrCustomer) {
            if (cust instanceof Member) {
                for (FixedBill bill : ((Member) cust).getArrFixedBill()) {
                    data.add(new TranHis(cust, bill));
                }
            } else {
                data.add(new TranHis(cust, ((NonMember) cust).getFixedBill()));
            }
        }
    }
}
