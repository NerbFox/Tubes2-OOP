package org.posapp.view.settings;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class plugin_settings extends Pane {
    public plugin_settings() {
        // stroke border
        setStyle("-fx-background-color: #D9D9D966;");
        setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 4px;");
        getChildren().add(new Label("ini Tab plugin"));
        // make a label box with text No plugin loaded

    }

}
