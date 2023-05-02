package org.posapp.view.customer_list_info;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomerInfoDetail extends GridPane {
    private Label membershipStatusLabel;
    private TextField nameTextField;
    private TextField phoneTextField;
    private Label pointsLabel;
    private ComboBox<String> membershipComboBox;

    public CustomerInfoDetail() {
        // Set the pane's properties
        setPadding(new Insets(30));
        setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
        setPrefWidth(480);
        setPrefHeight(600);
        setVgap(0);
        setHgap(10);

        createHeaders();
        createCustomerInfo();
        createLogics();
    }

    private void createHeaders() {
        // Create the title label
        Label titleLabel = new Label("Customer ID");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
        titleLabel.setAlignment(Pos.TOP_CENTER);
        titleLabel.setPrefWidth(480);
        add(titleLabel, 0, 0);

        // Create the membership status label
        membershipStatusLabel = new Label("Membership Status");
        membershipStatusLabel.setStyle("-fx-font-size: 24px;");
        membershipStatusLabel.setAlignment(Pos.CENTER);
        membershipStatusLabel.setPrefWidth(480);
        add(membershipStatusLabel, 0, 1);
    }

    private void createCustomerInfo() {
        // Create the customer info section
        VBox customerInfoBox = new VBox(5);
        customerInfoBox.setPadding(new Insets(30, 0, 30, 0));
        customerInfoBox.setAlignment(Pos.CENTER);
        customerInfoBox.setPrefWidth(480);

        // Name label and text field
        HBox nameBox = new HBox(100);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        Label nameLabel = new Label("Name   :");
        nameLabel.setStyle("-fx-font-size: 22px;");
        nameTextField = new TextField();
        nameBox.getChildren().addAll(nameLabel, nameTextField);

        // Phone label and text field
        HBox phoneBox = new HBox(100);
        phoneBox.setAlignment(Pos.CENTER_LEFT);
        Label phoneLabel = new Label("Phone  :");
        phoneLabel.setStyle("-fx-font-size: 22px;");
        phoneTextField = new TextField();
        phoneBox.getChildren().addAll(phoneLabel, phoneTextField);

        // Points label
        HBox pointsBox = new HBox(200);
        pointsBox.setAlignment(Pos.CENTER_LEFT);
        Label pointsTextLabel = new Label("Points  :");
        pointsTextLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel = new Label("20");
        pointsLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel.setTextFill(Color.GREEN);
        pointsBox.getChildren().addAll(pointsTextLabel, pointsLabel);

        // Add all the customer info components to the VBox
        customerInfoBox.getChildren().addAll(nameBox, phoneBox, pointsBox);

        // Add the customer info VBox to the root pane
        add(customerInfoBox, 0, 2);
    }

    private void createLogics() {
        // Create the transaction history button
        Button transactionHistoryButton = new Button("Transaction History");
        add(transactionHistoryButton, 0, 3);

        // Create the change membership status label
        Label changeMembershipStatusLabel = new Label("Change Membership Status");
        changeMembershipStatusLabel.setStyle("-fx-font-size: 17px;");
        changeMembershipStatusLabel.setPadding(new Insets(30,0,10,0));
        changeMembershipStatusLabel.setAlignment(Pos.CENTER);
        add(changeMembershipStatusLabel, 0, 4);

        // Create the membership status dropdown
        membershipComboBox = new ComboBox<>();
        membershipComboBox.getItems().addAll("a", "b", "c");
        membershipComboBox.setValue("a");
        add(membershipComboBox, 0, 5);

        // Create the save button
        Button saveButton = new Button("Save");
        saveButton.setFont(new Font(20));
        saveButton.setPadding(new Insets(8, 20, 8, 20));
        GridPane.setMargin(saveButton, new Insets(100, 0 ,0, 0));
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        add(saveButton, 0, 6);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
    }

    public String getName() {
        return nameTextField.getText();
    }

    public String getPhone() {
        return phoneTextField.getText();
    }

    public String getMembershipStatus() {
        return membershipComboBox.getValue();
    }
}