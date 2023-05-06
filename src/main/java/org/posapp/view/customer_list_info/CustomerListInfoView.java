package org.posapp.view.customer_list_info;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.posapp.model.Customer;
import org.posapp.model.Member;
import org.posapp.model.NonMember;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class CustomerListInfoView extends Application {
    private Scene scene;
    private GridPane root;
    private CustomerInfoDetail detail;

    @Override
    public void start(Stage stage) {

        root = new GridPane();
        root.setPadding(new Insets(30));
        root.setBackground(new Background(new BackgroundFill(
                Color.WHITE, null, null
        )));

        makeLeftSection();
        makeRightSection();


        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    private void makeLeftSection() {
        String[] headers = new String[] {"Customer ID", "Membership Status"};
        String[] attributes = new String[] {"idCust", "memberStatus"};
        Customer[] data = new Customer[]{
                new NonMember(1, 1),
                new Member(2, "Bob", "0812241231", new ArrayList<Integer>(Arrays.asList(1,2,3)), 2000, false, false),
                new Member(3, "Charlie", "089623734", new ArrayList<Integer>(Arrays.asList(4,5,6)), 3000, false, true),
                new Member(4, "Dodo", "082136237", new ArrayList<Integer>(Arrays.asList(7,8,9)), 4000, true, true)
        };
        FixedSizeTable<Customer> CustomerList = new FixedSizeTable<Customer>(600, 480, headers, attributes, data, this::onRowSelect);
        Label titleLabel = new Label("Customer List and Info");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(CustomerList, 0, 1);
        gridPane.setPadding(new Insets(10, 20, 10, 20));
        GridPane.setHalignment(CustomerList, HPos.CENTER);

        gridPane.setBackground(new Background(new BackgroundFill(
                Color.WHITE, null, null
        )));
        root.add(gridPane, 0, 0);
    }

    private void makeRightSection() {
        detail = new CustomerInfoDetail(this::onSaveHandler);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.add(detail, 0, 0);
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.WHITE, null, null
        )));
        gridPane.setMargin(detail, new Insets(60, 0, 0,0));

        root.add(gridPane, 1, 0);
    }

    private void onRowSelect(Customer selectedItem) {
        detail.setTextTitleID(selectedItem.getIdCust().toString());
        detail.setTextMembershipStatus(selectedItem.getMemberStatus());

        if (selectedItem instanceof Member) {
            detail.setTextName(((Member) selectedItem).getName());
            detail.setTextPhone(((Member) selectedItem).getPhone());
            detail.setTextPoints(((Member) selectedItem).getPoin().toString());
        } else {
            detail.clear();
        }
        detail.setComboBox(selectedItem);
    }

    private void onSaveHandler(Map<String, String> savedValue) {
        System.out.println(savedValue.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}
