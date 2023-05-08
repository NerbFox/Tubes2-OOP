package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import java.io.File;
import javafx.stage.Stage;
import org.posapp.model.datastore.Datastore;

public class storage_dir extends Pane {
    private String path;
    public storage_dir() {
        int width = 450;
        int height = 50;
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 4px;");

        // rectangle box with text No path directory
        Label labelFormat = new Label("No path directory");
        // set box to width
        labelFormat.setStyle("-fx-border-color: #666666; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #eeeeee; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;");
        labelFormat.setPrefSize(width, height);
        labelFormat.setLayoutX(160);
        labelFormat.setLayoutY(35);
        labelFormat.setAlignment(Pos.CENTER);
        getChildren().add(labelFormat);

        Button ChooseDir = new Button();
        ChooseDir.setText("Choose Storage Directory");
        ChooseDir.setStyle("-fx-border-color: #555555; " +
                "-fx-border-width: 1px; " +
                "-fx-background-color: #b7b7b7; " +
                "-fx-padding: 15px;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: #ffffff;");
        ChooseDir.setPrefSize(width, height);
        ChooseDir.setLayoutX(160);
        ChooseDir.setLayoutY(130);
        ChooseDir.setAlignment(Pos.CENTER);
        ChooseDir.setOnAction(event -> {
            labelFormat.setText("Directory");
            // change color to black
            // open file explorer
            labelFormat.setTextFill(Color.BLACK);

            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(new Stage());

            if(selectedDirectory != null) {
                // do something with the selected directory
                path = selectedDirectory.getAbsolutePath();
                // get base name of directory
                labelFormat.setText(selectedDirectory.getName());
                System.out.println(path);
                Datastore.getInstance().changeDirectory(path);
            }

        });
        getChildren().add(ChooseDir);
    }

    void openFileExplorer() {
        // open file explorer
    }
}