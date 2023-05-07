package org.posapp.view.cashier_menu;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BillItems extends Group {


    public BillItems() {
        super();
        VBox mainContent = new VBox();
        VBox billItemDisplay = new VBox(5);
        billItemDisplay.setPadding(new Insets(6,10,6,20));

        // Add CashierItem to the VBox
        for (int i = 0; i < 5; i++) {
            billItemDisplay.getChildren().add(new BillItem());
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
}
