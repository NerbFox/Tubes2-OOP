package org.posapp.view.settings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.posapp.view.TabContent;
import javafx.geometry.Side;


import java.awt.*;

public class tab_settings extends BorderPane {
    // attributes
    private Label titleLabel;
    private Button plugin;
    private Button format_file;
    private Button storage_dir;
    private int xBox = 10;
    private int yBox = 70;
    private int heightBox = 580;
//    public class TabSettings extends BorderPane {

    private VBox leftBox;

        public tab_settings(String nama) {
            System.out.println("TabSettings" + nama);
            setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
            createHeaders();
            createLeftBox();
            createRightBox();
        }

    private void createHeaders() {
        Rectangle rectTitle = new Rectangle();
        rectTitle.setStyle("-fx-fill: #000000;");
        rectTitle.setWidth(300);
        rectTitle.setHeight(45);
//        rectTitle.setY(0);
        setTop(rectTitle);

        titleLabel = new Label("Settings");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 36px; -fx-text-fill: #524f4f;");
        titleLabel.setLayoutX(320);
        titleLabel.setLayoutY(0);
        titleLabel.setAlignment(Pos.CENTER);
        setTop(titleLabel);

    }

    private void createLeftBox() {
        leftBox = new VBox();
        leftBox.setPrefWidth(70);
//        leftBox.setPrefHeight(580);
        leftBox.setStyle("-fx-background-color: #D9D9D9;");
        // set stroke
        leftBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");



        plugin = createButton("Plugin");
        format_file = createButton("Format File");
        storage_dir = createButton("Storage Directory");

        leftBox.getChildren().addAll(plugin, format_file, storage_dir);
        setCenter(leftBox);
    }

    private void createRightBox() {
        TabPane innerTabPane = new TabPane();
        innerTabPane.setPrefSize(800, 700);
        innerTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        innerTabPane.setSide(Side.LEFT);

        Tab pluginTab = new Tab("Plugin");
        pluginTab.setContent(new plugin_settings());

        Tab formatFileTab = new Tab("Format File");
        formatFileTab.setContent(new Label("Format File Tab Content"));

        Tab storageTab = new Tab("Storage Directory");
        storageTab.setContent(new Label("Storage Directory Tab Content"));

        innerTabPane.getTabs().addAll(pluginTab, formatFileTab, storageTab);
        setRight(innerTabPane);

        // if button clicked, change the color of button to darker and select the tab
        plugin.setOnAction(e -> innerTabPane.getSelectionModel().select(pluginTab));
//        plugin.setOnAction(e -> plugin.setStyle("-fx-background-color: #BDBDBD; -fx-font-size: 16px; -fx-text-fill: black;"));
        format_file.setOnAction(e -> innerTabPane.getSelectionModel().select(formatFileTab));
        storage_dir.setOnAction(e -> innerTabPane.getSelectionModel().select(storageTab));
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16px; -fx-text-fill: black;");
        // set to left alignment
        button.setAlignment(Pos.CENTER_LEFT);
        // set stroke
        button.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
//        button.setCursor(Cursor.HAND);
        return button;
    }


//    public tab_settings(String nama) {
//        setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
//        createHeaders();
//        createLeftBox();
//        createRightBox();
//
//        TabPane innerTabPane = new TabPane();
//        Tab innerTab1 = new Tab("Inner Tab 1");
//        Tab innerTab2 = new Tab("Inner Tab 2", new plugin_settings());
//        innerTabPane.getTabs().addAll(innerTab1, innerTab2);
//
//        this.setCenter(innerTabPane);
//        // Set the pane's properties
////
//
//    }
//    private void createHeaders() {
//        int x = 400;
//        // Create the title label
//        Rectangle rectTitle = new Rectangle();
//        rectTitle.setStyle("-fx-fill: #767676;");
//        rectTitle.setWidth(300);
//        rectTitle.setHeight(45);
//        // set to center
//        rectTitle.setX(x);
//        rectTitle.setY(5);
//        getChildren().add(rectTitle);
//
//
//        titleLabel = new Label("Settings");
//        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 36px; -fx-text-fill: white;");
////        titleLabel.setAlignment(Pos.TOP_CENTER);
//        titleLabel.setLayoutX(x+100);
//        titleLabel.setLayoutY(0);
//
//        getChildren().add(titleLabel);
//    }
//
//    private void createLeftBox() {
//        BOX(xBox, yBox, 260, heightBox, "#D9D9D9");
//
//        plugin = createBoxButton("Plugin", 28, 80, 225, 40, "#76767644");
//        getChildren().add(plugin);
//
//
////        TabPane tabPane = new TabPane();
//        Tab tab1 = new Tab("hmmm", new Label("Content of tab 1"));
//        Tab tab2 = new Tab("plugin");
////        Pane pluginPane = new Pane(); // create a new pane for the plugin page
////        pluginPane.setStyle("-fx-background-color: #FF8849;");
////        pluginPane.getChildren().add(new Label("ini Tab plugin"));
////        tab2.setContent(pluginPane);
////
////
////        tabPane.getTabs().addAll(tab1, tab2);
//        tab2.setContent(new TabContent("plugin"));
//
//
//        // Add the TabPane to the scene
////        BorderPane root = new BorderPane(self);
////        Scene scene = new Scene(root, 700, 580);
//
//        // Add a button to switch to the second tab
//        //        Button plugin = new Button("Plugin");
//        plugin.setOnAction(e -> {
//            // Switch to the second tab
//            System.out.println("plugin");
////            .getSelectionModel().select(1);
//        });
//
////            System.out.println("plugin");
////
////            // change tab to plugin
////
////
////
////
////        });
//    }
//
//    private Scene createPluginScene() {
//        // Create the root pane
//        BorderPane rootPane = new BorderPane();
//
//        // Create the scene
//        Scene scene = new Scene(rootPane, 690, heightBox-10);
//
//
//        return scene;
//    }
//
//    private void createRightBox() {
//        BOX(xBox+260, yBox, 700, heightBox, "#D9D9D966");
//
////        format_file = new Button("Format File");
////        format_file.setStyle("-fx-font-size: 24px;");
////        format_file.setAlignment(Pos.CENTER);
////        format_file.setPrefWidth(width);
////        format_file.setPrefHeight(height);
////        format_file.setLayoutX(x);
////        format_file.setLayoutY(y);
////        getChildren().add(format_file);
//    }
//
//    private void BOX(int x, int y, int width, int height, String color) {
//        // Create the customer info section
//        Rectangle rect = new Rectangle();
//        rect.setStyle("-fx-fill: "+color+";");
//        rect.setWidth(width);
//        rect.setHeight(height);
//        rect.setX(x);
//        rect.setY(y);
//        rect.setStroke(Color.BLACK);
//        rect.setStrokeWidth(1);
//        getChildren().add(rect);
//    }
//
//    private Button createBoxButton(String text, int x, int y, int width, int height, String color) {
//        Button button = new Button(text);
//        button.setPrefSize(width, height);
//        button.setLayoutX(x);
//        button.setLayoutY(y);
//        button.setStyle("-fx-background-color: " + color + "; -fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-shape: \"M 0 0 h " + width + " v " + height + " h -" + width + " v -" + height + " Z\";");
//        return button;
//    }
//
//    public static String colorToHex(Color color) {
//        String hexString = String.format("#%02X%02X%02X%02X",
//                (int) (color.getRed() * 255),
//                (int) (color.getGreen() * 255),
//                (int) (color.getBlue() * 255),
//                (int) (color.getOpacity() * 255));
//        return hexString;
//    }

}
