package org.posapp.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.posapp.view.History.HistoryView;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;
import org.posapp.view.settings.tab_settings;

import java.util.Arrays;
import java.util.List;


public class DisplayManager extends Application {

    private ToolBar toolbar;
    private TabPane tabPane;

    private final List<String> unduplicableTab = Arrays.asList("Tab1", "Tab4");

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();


        // Membuat toolbar
        toolbar = toolBar();
        root.setTop(toolbar);

        // TabPane
        tabPane = new TabPane();
        root.setCenter(tabPane);
        Tab tab = new Tab("MBV");
        tab.setContent(new HistoryView("MBV"));
        tabPane.getTabs().add(tab);

        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void play() {
        launch();
    }

    private ToolBar toolBar() {
        ToolBar toolbar = new ToolBar();

        // unduplicable
        Button btn1 = new Button("Tab1");
        btn1.setOnAction(new AddTabHandler("Tab1"));

        // duplicable
        Button btn2 = new Button("Tab2");
        btn2.setOnAction(new AddTabHandler("Tab2"));

        // dropdownbutton
        SplitMenuButton dropDownBtn = new SplitMenuButton();
        dropDownBtn.setText("Menu");

        MenuItem btn3 = new MenuItem("Tab3");
        btn3.setOnAction(new AddTabHandler("Tab3"));

        MenuItem btn4 = new MenuItem("Tab4");
        btn4.setOnAction(new AddTabHandler("Tab4"));

        dropDownBtn.getItems().addAll(btn3, btn4);

        toolbar.getItems().addAll(btn1, btn2, dropDownBtn);

        return toolbar;
    }

    private class AddTabHandler implements EventHandler<ActionEvent> {

        private String nama;
        AddTabHandler(String nama){
            super();
            this.nama = nama;
        }

        @Override
        public void handle(ActionEvent event){
            boolean duplicable = true;
            Tab tabToFind = null;
            for (Tab tab : tabPane.getTabs()){
                if (unduplicableTab.contains(tab.getText()) && tab.getText().equals(nama)){
                    duplicable = false;
                    tabToFind = tab;
                    break;
                }
            }
            if (duplicable) {
                Tab newTab = new Tab(this.nama);
//                switch (nama) {
//                    case "Tab1" -> newTab.setContent(new TabContent(nama));
//                    case "Tab2" -> newTab.setContent(new TabContent(nama));
//                    case "Tab3" -> newTab.setContent(new TabContent(nama));
//                    case "Tab4" -> newTab.setContent(new TabContent(nama));
//                    default -> newTab.setContent(new TabContent(nama));
//                }
                if (nama.equals("Tab1")) newTab.setContent(new TabContent(nama));
                else if (nama.equals("Tab2")) newTab.setContent(new tab_settings(nama));
                else if (nama.equals("Tab3")) newTab.setContent(new ManajemenBarangView(nama));

                tabPane.getTabs().add(newTab);
                tabPane.getSelectionModel().select(newTab);
            } else {
                tabPane.getSelectionModel().select(tabToFind);
            }
        }
    }
}