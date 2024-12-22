package com.example.calculatorfx;

import com.example.calculatorfx.controller.CalculatorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends  Application{
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage){
        CalculatorController calculatorController = new CalculatorController();
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.setScene(new Scene(ViewBuilder.loadView("CalculatorView.fxml", calculatorController)));
        stage.show();
    }
}