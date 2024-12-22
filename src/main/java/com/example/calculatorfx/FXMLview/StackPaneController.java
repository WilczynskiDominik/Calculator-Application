package com.example.calculatorfx.FXMLview;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class StackPaneController {

    //constructor it takes reference to stackPane, with and height to set StackPane
    public StackPaneController(StackPane stackPane, int width, int height){
        stackPane.setPrefWidth(width);
        stackPane.setPrefHeight(height);
        //looking for pane that contains history of equations
        StackPane.setAlignment(stackPane.lookup("#sidePane"), Pos.TOP_RIGHT);
    }
}