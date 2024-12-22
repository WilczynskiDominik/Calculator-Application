package com.example.calculatorfx;

import com.example.calculatorfx.FXMLview.FXMLController;
import com.example.calculatorfx.FXMLview.HboxController;
import com.example.calculatorfx.FXMLview.PaneController;
import com.example.calculatorfx.FXMLview.StackPaneController;
import com.example.calculatorfx.controller.CalculatorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Class that builds the view of an application
public class ViewBuilder {

    //width and height of an application
    static int APPLICATION_WIDTH = 400;
    static int APPLICATION_HEIGHT = 480;

    //method that is responsible for loading the view, it takes fxml path and calculator controller reference
    public static Parent loadView(String fxmlPath, CalculatorController calculatorController){
        try{
            //instance of FXMLLoader
            FXMLLoader loader = new FXMLLoader(ViewBuilder.class.getResource((fxmlPath)));
            //instance of parent, contains tree of children. Look in fxml.path to understood
            Parent root = loader.load();

            //instance of FXMLController
            FXMLController fxmlController = loader.getController();
            //activating initialize method that takes calculatorController reference
            fxmlController.initialize(calculatorController);

            //look for stackPane in fxml to use StackController in it for styling
            StackPane stackPane = (StackPane)root.lookup("#stackPane");
            new StackPaneController(stackPane, APPLICATION_WIDTH, APPLICATION_HEIGHT);

            //creating list of HBoxes to pass to hBoxSetter
            List <HBox> hBoxes = new ArrayList<>();
            hBoxSetter(hBoxes, root);

            //height and width of side pane where history of equations is shown
            int paneHeight = APPLICATION_HEIGHT - 100;
            int paneWidth = APPLICATION_WIDTH - (APPLICATION_WIDTH/4);
            //looking for Pane in fxml to use PaneController in it for styling
            Pane pane = (Pane) root.lookup("#sidePane");
            new PaneController(pane, paneWidth, paneHeight);

            return root;
        }catch (IOException e){
            return new BorderPane();
        }
    }
    //method responsible for styling hBoxes
    private static void hBoxSetter(List<HBox> hBoxes, Parent root){
        int hBoxesInStackPane = 6;
        int hBoxHeight = APPLICATION_HEIGHT/hBoxesInStackPane;
        for(int i = 1; i <= hBoxesInStackPane; i++){
            HBox hbox = (HBox) root.lookup("#hbox" + i);
            hBoxes.add(hbox);
        }
        for(HBox hBox: hBoxes){
            new HboxController(hBox, APPLICATION_WIDTH, hBoxHeight);
        }
    }
}
