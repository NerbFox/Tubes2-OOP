package org.posapp.view.customer_list_info;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.converter.IntegerStringConverter;
import org.posapp.model.Customer;
import org.posapp.model.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class CustomerInfoDetail extends GridPane {
    private Label membershipStatusLabel;
    private Label titleLabel;
    private TextField nameTextField;
    private TextField phoneTextField;
    private Label pointsLabel;
    private ComboBox<String> membershipComboBox;
    private Button transactionHistoryButton;
    private Button saveButton;

    public CustomerInfoDetail(Consumer<Map<String, String>> saveHandler) {
        // Set the pane's properties
        setPadding(new Insets(30));
        setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
        setPrefWidth(480);
        setPrefHeight(600);
        setVgap(0);
        setHgap(10);

        createHeaders();
        createCustomerInfo();
        createLogics(saveHandler);
    }
    private void createHeaders() {
        // Create the title label
        titleLabel = new Label("Customer ID");
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
        nameTextField.setDisable(true);
        nameBox.getChildren().addAll(nameLabel, nameTextField);

        // Phone label and text field
        HBox phoneBox = new HBox(100);
        phoneBox.setAlignment(Pos.CENTER_LEFT);
        Label phoneLabel = new Label("Phone  :");
        phoneLabel.setStyle("-fx-font-size: 22px;");
        phoneTextField = new TextField();
        phoneTextField.setDisable(true);
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        phoneTextField.setTextFormatter(formatter);
        phoneBox.getChildren().addAll(phoneLabel, phoneTextField);

        // Points label
        HBox pointsBox = new HBox(200);
        pointsBox.setAlignment(Pos.CENTER_LEFT);
        Label pointsTextLabel = new Label("Points  :");
        pointsTextLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel = new Label("");
        pointsLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel.setTextFill(Color.GREEN);
        pointsBox.getChildren().addAll(pointsTextLabel, pointsLabel);

        // Add all the customer info components to the VBox
        customerInfoBox.getChildren().addAll(nameBox, phoneBox, pointsBox);

        // Add the customer info VBox to the root pane
        add(customerInfoBox, 0, 2);
    }
    private void createLogics(Consumer<Map<String, String>> saveHandler) {
        // Create the transaction history button
        transactionHistoryButton = new Button("Transaction History");
        transactionHistoryButton.setDisable(true);
        add(transactionHistoryButton, 0, 3);

        // Create the change membership status label
        Label changeMembershipStatusLabel = new Label("Change Membership Status");
        changeMembershipStatusLabel.setStyle("-fx-font-size: 17px;");
        changeMembershipStatusLabel.setPadding(new Insets(30,0,10,0));
        changeMembershipStatusLabel.setAlignment(Pos.CENTER);
        add(changeMembershipStatusLabel, 0, 4);

        // Create the membership status dropdown
        membershipComboBox = new ComboBox<>();
        membershipComboBox.setDisable(true);
        add(membershipComboBox, 0, 5);

        // Create the save button
        saveButton = new Button("Save");
        saveButton.setFont(new Font(20));
        saveButton.setPadding(new Insets(8, 20, 8, 20));
        GridPane.setMargin(saveButton, new Insets(100, 0 ,0, 0));
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        saveButton.setOnAction(event -> this.onSaveButton(saveHandler));
        saveButton.setDisable(true);
        add(saveButton, 0, 6);
        GridPane.setHalignment(saveButton, HPos.RIGHT);
    }
    public void setTextMembershipStatus(String newStatus) {
        membershipStatusLabel.setText(newStatus);
    }
    public void setTextTitleID(String newID) {
        titleLabel.setText(newID);
    }
    public void setTextPoints(String newPoints) {
        pointsLabel.setText(newPoints);
    }
    public void setTextName(String newName) {
        nameTextField.setDisable(false);
        transactionHistoryButton.setDisable(false);
        saveButton.setDisable(false);
        nameTextField.setText(newName);
    }
    public void setTextPhone(String newPhone) {
        phoneTextField.setDisable(false);
        phoneTextField.setText(newPhone);
    }
    public void setComboBox(Customer val) {
        membershipComboBox.setDisable(false);
        ObservableList<String> options;
        if (val instanceof Member) {
            options = FXCollections.observableArrayList("VIP Member", "Normal Member", "Deactivated");
            if (((Member) val).getFrozen()) membershipComboBox.setValue("Deactivated");
            else if (((Member) val).getVipStatus()) membershipComboBox.setValue("VIP Member");
            else membershipComboBox.setValue("Normal Member");
        } else {
            options = FXCollections.observableArrayList("Non-Member", "Normal Member");
            membershipComboBox.setValue("Non-Member");
        }
        membershipComboBox.setItems(options);
    }
    public void clear() {
        setTextPoints("");
        setTextName("");
        setTextPhone("");
    }
    private void onSaveButton(Consumer<Map<String, String>> saveHandler) {
        Map<String, String> retMap = new HashMap<>();
        retMap.put("id", titleLabel.getText());
        retMap.put("name", nameTextField.getText());
        retMap.put("phone", phoneTextField.getText());
        retMap.put("status", membershipComboBox.getValue());
        saveHandler.accept(retMap);
    }
}