package org.posapp.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

// extends Pane atau Turunannya
public class TabContent extends Pane {
    public TabContent(String nama) {
        setStyle("-fx-background-color: #FF8849;");
        getChildren().add(new Label("ini Tab " + nama));
    }
}
