package org.posapp.view.cashier_menu;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.posapp.model.Barang;
import org.posapp.model.NonFixedBill;
import org.posapp.model.datastore.Datastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BillItems extends Group {
    private HashMap<Barang, Integer> barangNonFixBill;
    private ArrayList<Barang> tmpListBarang = Datastore.getInstance().getArrBarang();

    public BillItems(NonFixedBill nonFB, Consumer<Barang> onDelete) {
        super();
        VBox mainContent = new VBox();
        VBox billItemDisplay = new VBox(5);
        billItemDisplay.setPadding(new Insets(6,10,6,20));

//        barangNonFixBill = getListBarang(nonFB,tmpListBarang);
//        System.out.println(barangNonFixBill.size());
        // Add CashierItem to the VBox

        barangNonFixBill = nonFB.getMapBarang();
        for (Map.Entry<Barang, Integer> entry : barangNonFixBill.entrySet()) {
            billItemDisplay.getChildren().add(new BillItem(entry.getKey(),entry.getValue(), onDelete));
        }


        Label total = new Label("USD 100");

        // Create a ScrollPane and set its content to the VBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(378);
        scrollPane.setPrefWidth(351);
        scrollPane.setContent(billItemDisplay);

        // Set group content to ScrollPane
        this.getChildren().add(scrollPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public HashMap<Barang, Integer> getListBarang(NonFixedBill nonFixedBill, ArrayList<Barang> listBarang) {
        HashMap<Barang, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : nonFixedBill.getMapId().entrySet()) {
            Integer idBarang = entry.getKey();
            Integer quantity = entry.getValue();
            for (Barang barang : listBarang) {
                if (barang.getIdBarang().equals(idBarang)) {
                    result.put(barang, quantity);
                    break;
                }
            }
        }
        return result;
    }
}
