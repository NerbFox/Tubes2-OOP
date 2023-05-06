package org.posapp.view.custom_components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FixedSizeSearchBar extends TextField {
    private final HandlerSearch handlerSearch;
    public FixedSizeSearchBar(int width, int height, String defaultText, HandlerSearch handler) {
        this.setPromptText(defaultText);
        this.setPrefSize(width, height);
        this.handlerSearch = handler;
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleTextChange(oldValue, newValue);
            }
        });
    }

    private void handleTextChange(String oldValue, String newValue){
        handlerSearch.onSearch(oldValue, newValue);
    }
}
