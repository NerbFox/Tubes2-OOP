package org.posapp.view.cashier_menu;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.posapp.model.Barang;
import org.posapp.model.datastore.Datastore;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CashierItems extends Group {
    private VBox content;
    private ArrayList<Barang> contentBarang;

    public CashierItems(ArrayList<Barang> listBarang) {
        super();
        // Create a VBox to hold the content
        contentBarang = listBarang;
        content = new VBox();
        content.setPadding(new Insets(10));

        // Add CashierItem to the VBox
        for (int i = 0; i < listBarang.size(); i++) {
            content.getChildren().add(new CashierItem(listBarang.get(i)));
            if (i % 2 == 1) {
                content.getChildren().get(i).setStyle("-fx-background-color: #D9D9D9");
            }
        }

        // Create a ScrollPane and set its content to the VBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(498);
        scrollPane.setPrefWidth(649);
        scrollPane.setContent(content);

        // Set group content to ScrollPane
        this.getChildren().add(scrollPane);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}

