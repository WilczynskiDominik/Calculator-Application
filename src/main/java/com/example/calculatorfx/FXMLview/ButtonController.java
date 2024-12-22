package com.example.calculatorfx.FXMLview;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonController {
    //final variable of button
    final private Button button;
    //final variable of font, contains font type and size
    final private Font font = new Font("Lato" , 20);

    //constructor that takes buttons reference, width and height
    public ButtonController(Button button, int width, int height){
        this.button = button;
        this.button.setPrefWidth(width);
        this.button.setPrefHeight(height);
        buttonStyling();
    }
    //method that is responsible for styling the button
    private void buttonStyling(){
        fontSet();
        colorSet();
        moseoverSet();
    }
    //method that is responsible for set the font
    private void fontSet(){
        this.button.setFont(this.font);
    }
    //method that is responsible for set the color of button
    private void colorSet(){
        this.button.setBackground(new Background(new BackgroundFill(Color.rgb(235,235,235,0.6), CornerRadii.EMPTY, null)));
    }
    //method that is responsible for set the color of button while moseover
    private void moseoverSet(){
        this.button.setOnMouseEntered(_ -> this.button.setBackground(new Background(new BackgroundFill(Color.rgb(200,200,200,0.8), CornerRadii.EMPTY, null))));
        this.button.setOnMouseExited(_ -> this.button.setBackground(new Background(new BackgroundFill(Color.rgb(235,235,235,0.6), CornerRadii.EMPTY, null))));
    }
}
