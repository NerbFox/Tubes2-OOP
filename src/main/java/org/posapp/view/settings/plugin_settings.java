package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class plugin_settings extends Pane {
    public plugin_settings() {
        setStyle("-fx-background-color: #FF8849;");
        getChildren().add(new Label("ini Tab plugin"));
    }

}
