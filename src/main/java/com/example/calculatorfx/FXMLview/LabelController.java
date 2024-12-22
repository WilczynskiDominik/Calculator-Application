package com.example.calculatorfx.FXMLview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LabelController {

    //final variable that contains reference to label
    final private Label label;
    //final variable of font, contains font type and size
    final private Font font = new Font("Lato" , 20);

    //constructor that takes labels reference, width and height
    LabelController(Label label, int width, int height){
        this.label = label;
        this.label.setPrefHeight(height);
        this.label.setPrefWidth(width);
        labelStyling();
    }
    //method responsible for styling label
    private void labelStyling(){
        fontSet();
        alignmentSet();
    }
    //method responsible for setting font
    private void fontSet(){
        this.label.setFont(this.font);
    }
    //method responsible for setting an alignment of text
    private void alignmentSet(){
        this.label.setAlignment(Pos.BASELINE_CENTER);
    }
}
