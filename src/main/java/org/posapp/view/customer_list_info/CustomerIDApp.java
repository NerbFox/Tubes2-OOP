package org.posapp.view.customer_list_info;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerIDApp extends Application {

    private Label membershipStatusLabel;
    private TextField nameTextField;
    private TextField phoneTextField;
    private Label pointsLabel;
    private ComboBox<String> membershipComboBox;

    @Override
    public void start(Stage primaryStage) {
        // Create the root pane
        GridPane rootPane = new GridPane();
        rootPane.setPadding(new Insets(30));
        rootPane.setVgap(0);
        rootPane.setHgap(10);

        // Create the title label
        Label titleLabel = new Label("Customer ID");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");
        titleLabel.setAlignment(Pos.TOP_CENTER);
        titleLabel.setPrefWidth(480);
        rootPane.add(titleLabel, 0, 0);

        // Create the membership status label
        membershipStatusLabel = new Label("Membership Status");
        membershipStatusLabel.setStyle("-fx-font-size: 24px;");
        membershipStatusLabel.setAlignment(Pos.CENTER);
        membershipStatusLabel.setPrefWidth(480);
        rootPane.add(membershipStatusLabel, 0, 1);

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
        HBox pointsBox = new HBox(100);
        pointsBox.setAlignment(Pos.CENTER_LEFT);
        Label pointsTextLabel = new Label("Points :");
        pointsTextLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel = new Label("20");
        pointsLabel.setStyle("-fx-font-size: 22px;");
        pointsLabel.setTextFill(Color.GREEN);
        pointsBox.getChildren().addAll(pointsTextLabel, pointsLabel);

        // Add all the customer info components to the VBox
        customerInfoBox.getChildren().addAll(nameBox, phoneBox, pointsBox);

        // Add the customer info VBox to the root pane
        rootPane.add(customerInfoBox, 0, 2);

        // Create the transaction history button
        Button transactionHistoryButton = new Button("Transaction History");
        rootPane.add(transactionHistoryButton, 0, 3);

        // Create the change membership status label
        Label changeMembershipStatusLabel = new Label("Change Membership Status");
        changeMembershipStatusLabel.setStyle("-fx-font-size: 17px;");
        changeMembershipStatusLabel.setPadding(new Insets(30,0,10,0));
        changeMembershipStatusLabel.setAlignment(Pos.CENTER);
        rootPane.add(changeMembershipStatusLabel, 0, 4);

        // Create the membership status dropdown
        membershipComboBox = new ComboBox<>();
        membershipComboBox.getItems().addAll("a", "b", "c");
        membershipComboBox.setValue("a");
        rootPane.add(membershipComboBox, 0, 5);

        // Create the save button
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        rootPane.add(saveButton, 1, 5);

        // Create the scene and show the stage
        Scene scene = new Scene(rootPane, 480, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer ID");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}