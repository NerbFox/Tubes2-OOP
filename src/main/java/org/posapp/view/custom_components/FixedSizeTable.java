package org.posapp.view.custom_components;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.function.Consumer;

public class FixedSizeTable<T> extends TableView<T> {

    public FixedSizeTable(int height, int width, String[] headersName, String[] attributeName, T[] data, Consumer<T> onRowSelect) {
        // Create the columns dynamically based on the headers array
        for (int i = 0; i < headersName.length; i++) {
            TableColumn column = new TableColumn<T, Object>(headersName[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(attributeName[i]));
            getColumns().add(column);
        }
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        setPrefSize(width, height);

        // Add the data to the table
        setItems(FXCollections.observableArrayList(data));

        // Add a row selection event handler to the table
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    T selectedItem = getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        onRowSelect.accept(selectedItem);
                    }
                }
            }
        });
    }

}