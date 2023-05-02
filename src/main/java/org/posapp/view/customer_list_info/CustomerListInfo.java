package org.posapp.view.customer_list_info;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.posapp.view.custom_components.FixedSizeTable;
import org.posapp.view.custom_components.Person;

public class CustomerListInfo extends Application {
    private Scene scene;
    private GridPane root;

    @Override
    public void start(Stage stage) {

        root = new GridPane();
        makeLeftSection();
        makeRightSection();

        root.getColumnConstraints().addAll(
                new ColumnConstraints(540),
                new ColumnConstraints(540)
        );
        root.getRowConstraints().add(new RowConstraints(720));

        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    private void makeLeftSection() {
        String[] headers = new String[] {"Name", "Age"};
        Person[] data = new Person[] {
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        };
        FixedSizeTable CustomerList = new FixedSizeTable<Person>(600, 480, headers, data, this::onRowSelect);
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
        StackPane rightBox = new StackPane();
        rightBox.setBackground(new Background(new BackgroundFill(
                Color.GREEN, null, null
        )));
        root.add(rightBox, 1, 0);
    }

    private void onRowSelect(Person selectedItem) {
        System.out.println("Selected row: " + selectedItem.getName() + ", " + selectedItem.getAge());
        // Do something with the selected data here
    }

    public static void main(String[] args) {
        launch();
    }
}
