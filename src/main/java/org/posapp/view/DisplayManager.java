package org.posapp.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.posapp.view.History.HistoryView;
import org.posapp.view.LiveClock.LiveClock;
import org.posapp.view.ManajemenBarang.ManajemenBarangView;
import org.posapp.view.cashier_menu.CashierMenu;
import org.posapp.view.customer_list_info.CustomerListInfoView;
import org.posapp.view.settings.tab_settings;

import java.util.Arrays;
import java.util.List;


public class DisplayManager extends Application {

    private ToolBar toolbar;
    private TabPane tabPane;

    private final List<String> unduplicableTab = Arrays.asList("Kasir");

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();


        // Membuat toolbar
        toolbar = toolBar();
        root.setTop(toolbar);

        // TabPane
        tabPane = new TabPane();
        root.setCenter(tabPane);
        Tab tab = new Tab("Main");
        tab.setContent(new LiveClock("Main"));
        tab.setClosable(false);
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
        // Kasir, MAnajemenBarang, History, Detail Customer, Setting,

        // unduplicable
        Button btn1 = new Button("Kasir");
        btn1.setOnAction(new AddTabHandler("Kasir"));

        // duplicable
        Button btn2 = new Button("Barang");
        btn2.setOnAction(new AddTabHandler("Barang"));

        // dropdownbutton
        SplitMenuButton dropDownBtn = new SplitMenuButton();
        dropDownBtn.setText("Additional");

        MenuItem btn3 = new MenuItem("Setting");
        btn3.setOnAction(new AddTabHandler("Setting"));

        MenuItem btn4 = new MenuItem("Customer");
        btn4.setOnAction(new AddTabHandler("Customer"));

        MenuItem btn5 = new MenuItem("History");
        btn5.setOnAction(new AddTabHandler("History"));
//
//        MenuItem btn6 = new MenuItem("Tab6");
//        btn6.setOnAction(new AddTabHandler("Tab6"));

        dropDownBtn.getItems().addAll(btn4, btn5, btn3);

        toolbar.getItems().addAll(dropDownBtn, btn1, btn2);

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
                if (nama.equals("Setting")) newTab.setContent(new tab_settings(nama));
                else if (nama.equals("Kasir")) newTab.setContent(new CashierMenu());
                else if (nama.equals("Barang")) newTab.setContent(new ManajemenBarangView(nama));
//                else if (nama.equals("Tab4")) newTab.setContent(new TabContent(nama));
                else if (nama.equals("History")) newTab.setContent(new HistoryView(nama));
                else if (nama.equals("Customer")) newTab.setContent(new CustomerListInfoView());

                tabPane.getTabs().add(newTab);
                tabPane.getSelectionModel().select(newTab);
            } else {
                tabPane.getSelectionModel().select(tabToFind);
            }
        }
    }
}