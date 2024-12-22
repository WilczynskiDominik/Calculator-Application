package com.example.calculatorfx.FXMLview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaneController {
    //variable that contains reference to pane
    final private Pane pane;
    //variable contains width
    final private int WIDTH;
    //variable contains height
    final private int HEIGHT;

    //constructor takes reference to pane, with and height
    public PaneController(Pane pane, int width, int height){
        this.pane = pane;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.pane.setMaxSize(WIDTH, HEIGHT);
        paneStyling();
    }
    //method responsible for styling pane
    private void paneStyling(){
        labelStyling();
        colorSet();
    }
    //method responsible for styling label inside the pane
    private void labelStyling(){
        Label label = (Label)this.pane.lookup("#historyArea");
        new LabelController(label,this.WIDTH - 20, this.HEIGHT-20);
        new LabelController(label,this.WIDTH - 20, this.HEIGHT-20);
        label.setAlignment(Pos.TOP_RIGHT);

    }
    //method responsible for setting color of pane
    private void colorSet(){
        this.pane.setBackground(new Background(new BackgroundFill(Color.rgb(235,235,235), CornerRadii.EMPTY, null)));
    }


}
