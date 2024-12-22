package com.example.calculatorfx.FXMLview;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HboxController {

    //variable that contains value of button in hbox
    private int childrenInHbox;
    //variable that is ture when vbox is in hbox
    private boolean isVBox = false;
    //final variable of hbox
    private final HBox HBOX;
    //variable that contains width of hbox
    final int WIDTH;
    //variable that contains height of hbox
    final int HEIGHT;

    public HboxController(HBox HBOX,int WIDTH, int HEIGHT){
        this.HBOX = HBOX;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        this.HBOX.setPrefWidth(WIDTH);
        this.HBOX.setPrefHeight(HEIGHT);
        this.HBOX.setAlignment(Pos.CENTER);

        childrenCounter();
        childrenSizing();
    }
    //method that is responsible for size children in hbox
    private void childrenSizing(){
        if(isVBox){
            vBoxSizing();
            labelSizing();
        }
        buttonsSizing();
    }
    //method that is responsible for sizing vbox in vbox
    private void vBoxSizing() {
        int vBoxWidth = WIDTH / 4;
        VBox vBox;
        try{
             vBox = this.HBOX.getChildren().stream()
                    .filter(node -> node instanceof VBox)
                    .map(node -> (VBox) node)
                    .findFirst()
                    .orElse(new VBox());
        }catch (Exception e){
            return;
        }
        vBox.setPrefWidth(vBoxWidth);
        vBox.setPrefHeight(this.HEIGHT);
        buttonSizingInVBox(vBox);
    }
    //method that is responsible for sizing buttons in vbox
    private void buttonSizingInVBox(VBox vBox){
        int buttonWidth = WIDTH/4;
        int buttonHeight = HEIGHT/2;
        for(Node node : vBox.getChildren()) {
            if (node instanceof Button button) {
                new ButtonController(button, buttonWidth, buttonHeight);
            }
        }
    }
    //method that is responsible for sizing label
    private void labelSizing(){
        int labelWidth = WIDTH - (WIDTH / 4);
        Label label;
        try{
            label = this.HBOX.getChildren().stream()
                    .filter(node -> node instanceof Label)
                    .map(node -> (Label) node)
                    .findFirst()
                    .orElse(new Label());
        }catch (Exception e){
            return;
        }
        new LabelController(label, labelWidth, HEIGHT);
    }
    //method that size buttons evenly in hbox using all free space
    private void buttonsSizing(){
        int buttonWidth = WIDTH / childrenInHbox;
        for(Node node : this.HBOX.getChildren()) {
            if (node instanceof Button button) {
                new ButtonController(button, buttonWidth, HEIGHT);
            }
        }
    }
    //method that counts how many children are in hbox
    private void childrenCounter(){
        for(Node node : this.HBOX.getChildren()) {
            this.childrenInHbox += 1;
            if(node instanceof VBox){
                this.isVBox = true;
            }
        }
    }
}
