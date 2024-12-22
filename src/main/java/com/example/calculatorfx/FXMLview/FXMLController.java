package com.example.calculatorfx.FXMLview;

import com.example.calculatorfx.controller.CalculatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

//Class that handle the FXML file, which is charge of view
public class FXMLController{

    //an instance of CalculatorController
    private CalculatorController calculatorController;
    //variable that is true when user divide by zero
    private boolean isError = false;
    //variable that is true when side pane is open
    private boolean isSidePane = false;


    @FXML
    //an instance of Label, place where equation is appearing
    private Label calculationArea;
    @FXML
    //an instance of Label, place where history of equations is appearing
    private Label historyArea;
    @FXML
    //an instance of Pane, is normally hidden but when user clicked button with watch this pane is appearing with history of equation that were made
    private Pane sidePane;

    @FXML
    //method used when user clicked button with a number, this method set equation value to calculatorController
    protected void handleValueButton(ActionEvent event){
        try{
            getValue(event);
        }catch (IllegalStateException e){
            return;
        }
        calculationArea.setText(calculationAreaText());
    }
    @FXML
    //method used when user clicked button with "C", this method clear all equation
    protected void handleClearEquation(){
        calculatorController.clear();
        calculationArea.setText("");
        isError = false;
    }
    @FXML
    //method used when user clicked button with equation character, this method save equation character to calculatorController
    protected void handleCharacterEquation(ActionEvent event){
        if(!calculatorController.isOneEquationCharacterIn()){
            equationProtected();
        }
        try{
            calculatorController.setEquationCharacter(getCharacterFromButton(event));
        } catch (IllegalStateException e){
            return;
        }
        if((calculatorController.isEquationDone() && calculatorController.isEquationCharacterIn()) || (calculatorController.isEquationCharacterIn() && !calculatorController.getEquationValue(2).isEmpty())){
            equationProtected();
            calculationArea.setText(calculationAreaText());
        }
        if(calculatorController.isOneEquationCharacterIn()){
            handleEndOfEquation();
        }
        else{
            calculationArea.setText(calculationAreaText());
        }
    }
    @FXML
    //method used when user clicked button to change sign of a number from + to -, or - to +
    protected void handleChangeMark(){
        calculatorController.changeSign();
        calculationArea.setText(calculationAreaText());
    }
    @FXML
    //method used when user want to look at equations made before
    protected void handleHistory(){
        if(isSidePane){
            sidePane.setVisible(false);
            isSidePane = false;
        }
        else{
            sidePane.setVisible(true);
            historyArea.setText(calculatorController.getHistory());
            isSidePane = true;
        }
    }
    @FXML
    //method used when user clicked button with "=", this method finished equation
    protected void handleEndOfEquation(){
        equationProtected();
        if(!isError){
            calculationArea.setText(calculationAreaText());
            calculatorController.addHistory();
        }
    }

    @FXML
    //method initialized when star program
    public void initialize(CalculatorController calculatorController){
        this.calculatorController = calculatorController;
    }

    //method responsible for set equation value to calculatorController
    private void getValue(ActionEvent event){
        if(!calculatorController.isEquationDone()){
            String character = getCharacterFromButton(event);
            calculatorController.setEquationValue(character);
        }
    }
    //method responsible for get character from button that user clicked, throws exception when variable isError is true
    private String getCharacterFromButton(ActionEvent event) throws IllegalStateException{
        if(isError){
            throw new IllegalStateException("Error");
        }
        Button clickedButton = (Button) event.getSource();
        return clickedButton.getText();
    }
    //method responsible for finishing equation with protection program from dividing by zero
    private void equationProtected(){
        try{
            calculatorController.equation();
        }catch (Exception e){
            calculationArea.setText("ERROR");
            isError = true;
        }
    }
    //method that returns String equation text which is shown in the label
    private String calculationAreaText(){
        if(!calculatorController.isEquationCharacterIn()){
            return calculationAreaTextForOneValueInLabel();
        }else if(calculatorController.isEquationDone()){
            return calculationAreaTextForEndOfEquation();
        }
        else{
            return calculationAreaTextForTwoValuesInLabel();
        }
    }
    //method that returns String, in string is first value of equation
    private String calculationAreaTextForOneValueInLabel(){
        return addingBracketWhenNegativeValue(1);
    }
    //method that returns String, in string are first value of equation, equation character and second value of equation
    private String calculationAreaTextForTwoValuesInLabel(){
        return (calculationAreaTextForOneValueInLabel() +
            " " +
            calculatorController.getEquationCharacter() +
            " " +
            addingBracketWhenNegativeValue(2)); }
    //method that returns String, in string is finished equation text
    private String calculationAreaTextForEndOfEquation(){
        return (calculationAreaTextForTwoValuesInLabel() +
                " " +
                "=" +
                " " +
                calculatorController.getEndEquationValue());
    }
    //method that returns String, in string is value with bracket or not
    private String addingBracketWhenNegativeValue(int index){
        StringBuilder value = new StringBuilder();
        if(calculatorController.getEquationValue(index).contains("-")){
            value.append("(");
            value.append(calculatorController.getEquationValue(index));
            value.append(")");
            return value.toString();
        }
        return value.append(calculatorController.getEquationValue(index)).toString();
    }
}