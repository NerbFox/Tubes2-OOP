package org.posapp.view.customer_list_info;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import org.posapp.controller.command.Command;
import org.posapp.controller.command.RowSelectCommand;
import org.posapp.controller.command.SaveCommand;
import org.posapp.model.Customer;
import org.posapp.model.datastore.Datastore;
import org.posapp.view.custom_components.FixedSizeTable;

import java.util.Map;

public class CustomerListInfoView extends GridPane {
    private CustomerInfoDetail detail;
    private FixedSizeTable<Customer> customerList;

    public CustomerListInfoView() {
        setPadding(new Insets(10, 30, 30, 30));
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        makeLeftSection();
        makeRightSection();
    }

    private void makeLeftSection() {
        String[] headers = new String[]{"Customer ID", "Membership Status"};
        String[] attributes = new String[]{"idCust", "memberStatus"};

        Customer[] data = new Customer[Datastore.getInstance().getArrCustomer().size()];
        data = Datastore.getInstance().getArrCustomer().toArray(data);

        customerList = new FixedSizeTable<>(600, 480, headers, attributes, data, this::onRowSelect);
        Label titleLabel = new Label("Customer List and Info");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(customerList, 0, 1);
        gridPane.setPadding(new Insets(10, 20, 10, 20));
        GridPane.setHalignment(customerList, HPos.CENTER);

        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        add(gridPane, 0, 0);
    }

    private void makeRightSection() {
        detail = new CustomerInfoDetail(this::onSaveHandler);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.add(detail, 0, 0);
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        gridPane.setMargin(detail, new Insets(60, 0, 0, 0));

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(500);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        getRowConstraints().add(rowConstraints);

        add(gridPane, 1, 0);
    }

    private void onRowSelect(Customer selectedItem) {
        Command command = new RowSelectCommand(detail, selectedItem);
        command.execute();
    }

    private void onSaveHandler(Map<String, String> savedValue) {
        Command command = new SaveCommand(savedValue, customerList);
        command.execute();
    }
}