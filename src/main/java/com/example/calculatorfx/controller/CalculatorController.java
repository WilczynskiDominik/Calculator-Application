package com.example.calculatorfx.controller;

import com.example.calculatorfx.model.EquationHistoryModel;
import com.example.calculatorfx.model.MathModel;
import java.math.BigDecimal;
import java.util.Stack;

//Class that controls process of application
public class CalculatorController {

    //mathModel for calculator application
    private final MathModel mathModel = new MathModel();
    //equationHistoryModel is a class where made equations are stored
    private final EquationHistoryModel equationHistory = new EquationHistoryModel();
    //first value of equation in stringBuilder
    StringBuilder firstValueOfEquation = new StringBuilder();
    //second value of equation, if needed, in stringBuilder
    StringBuilder secondValueOfEquation = new StringBuilder();
    //boolean variable that is true when equation is done and it wasn't clear
    private boolean isEquationDone = false;
    //variable that equals value from equation
    private String endEquationValue;

    //method that returns equation character
    public String getEquationCharacter(){
        try{
            return mathModel.getEquationCharacter();
        }catch(Exception e){
            System.out.println("Error: can not get equation character " + e);
            return e.toString(); // nie wiem czy to dobrze
        }
    }
    //method that returns true when in equation is equation character
    public boolean isEquationCharacterIn(){
        return mathModel.isEquationCharacterIn();
    }
    //method that returns true when in equation is equation character which needs one value to solve equation
    public boolean isOneEquationCharacterIn(){ return mathModel.isOneValueEquationCharacterIn();}
    //method that returns true when equation is done and it wasn't clear
    public boolean isEquationDone(){ return this.isEquationDone;}
    //method that returns finished equation value in String
    public String getEndEquationValue(){return this.endEquationValue;}
    //method that return equation value in String by index
    public String getEquationValue(int index){
        try{
            switch (index) {
                case 1 -> {return firstValueOfEquation.toString();}
                case 2 -> {return secondValueOfEquation.toString();}
                default -> {return "Error";}
            }
        }catch(Exception e){
            return "Error: can not get a value " + e;
        }
    }

    //method that is responsible for set equation values
    public void setEquationValue(String character){
        if(!mathModel.isEquationCharacterIn()){
            firstDotProtection(firstValueOfEquation, character);
            doubleDotProtection(firstValueOfEquation, character);
        }else{
            firstDotProtection(secondValueOfEquation, character);
            doubleDotProtection(secondValueOfEquation, character);
        }
    }
    //method that is responsible for create equation value when user first clicked dot, then the value starts from zero
    private void firstDotProtection(StringBuilder stringBuilder, String character){
        if(stringBuilder.toString().isEmpty() && (character.equals("."))){
            stringBuilder.append(0);
            stringBuilder.append(character);
        }
    }
    //method that is responsible for protection to not put more than one dot in value
    private void doubleDotProtection(StringBuilder stringBuilder, String character){
        if(stringBuilder.toString().contains(".") && (character.equals("."))){
            return;
        }
        stringBuilder.append(character);
    }
    //method that is responsible for changing sing with equation value
    public void changeSign(){
        if(isEquationDone){
            return;
        }
        BigDecimal value;
        boolean isSecondValueOfEquationIsModifying = false;
        if(!secondValueOfEquation.isEmpty()){
            value = new BigDecimal(secondValueOfEquation.toString());
            secondValueOfEquation.setLength(0);
            isSecondValueOfEquationIsModifying = true;
        }else{
            value = new BigDecimal(firstValueOfEquation.toString());
            firstValueOfEquation.setLength(0);
        }
        BigDecimal negValue  = value.negate();
        if(isSecondValueOfEquationIsModifying){
            secondValueOfEquation.append(negValue);
        }else{
            firstValueOfEquation.append(negValue);
        }

    }

    //method that is responsible for set equation character
    public void setEquationCharacter(String character){
        try {
            if(firstValueOfEquation.isEmpty()){
                return;
            }
            mathModel.setEquationCharacter(character);
        }catch(Exception e){
            System.out.println("Error: can not set character " + e);
        }
    }
    //method that is responsible to clear all equation
    public void clear(){
        mathModel.clear();
        clearStringBuilders();
        isEquationDone = false;
    }
    //method that is responsible to clear firstValueOfEquation and secondValueOfEquation
    private void clearStringBuilders(){
        firstValueOfEquation.setLength(0);
        secondValueOfEquation.setLength(0);
    }

    //method that is responsible for equation, throws exception when value is divided by 0
    public void equation() throws Exception{
        try{
            if(isEquationDone && isEquationCharacterIn()){
                clearStringBuilders();
                firstValueOfEquation.append(endEquationValue);
                this.isEquationDone = false;
            }
            else if(mathModel.isOneValueEquationCharacterIn()){
                assemblyEquation("One-Value-Set");
            }
            else if(!secondValueOfEquation.isEmpty()){
                assemblyEquation("Two-Values-Set");
            }
        }catch (Exception e){
            throw new Exception("Error");
        }
    }
    //method that is responsible for assembly one or two values equation
    private void assemblyEquation(String text) throws Exception{
        switch (text){
            case "One-Value-Set" -> setValue(firstValueOfEquation.toString(), 1);
            case "Two-Values-Set" -> {
                setValue(firstValueOfEquation.toString(), 1);
                setValue(secondValueOfEquation.toString(), 2);
            }
        }
        this.isEquationDone = true;
        try{
            this.endEquationValue = mathModel.equation().toString();
        }catch (Exception e){
            throw new Exception("Error");
        }
    }
    //method that is responsible for set values of equation to mathModel variables
    private void setValue(String value, int index){
        try{
            switch (index) {
                case 1 -> mathModel.setFirstValue(new BigDecimal(value));
                case 2 -> mathModel.setSecondValue(new BigDecimal(value));
            }
        }catch(Exception e){
            System.out.println("Error: can not set value " + e);
        }
    }

    //method that is responsible to add equation into equation history
    public void addHistory(){
        this.equationHistory.add(this.mathModel);
    }
    //method that is responsible to return all equations made in string
    public String getHistory(){
        Stack<MathModel> tempStack = (Stack<MathModel>) this.equationHistory.getHistory().clone();
        StringBuilder stringBuilderEquationHistory = new StringBuilder();
        int numberOfEquationsMade = tempStack.size();
        for(int i = 0; i < numberOfEquationsMade; i++){
            stringBuilderEquationHistory.append("\n");
            stringBuilderEquationHistory.append(tempStack.pop().toString());
            stringBuilderEquationHistory.append("\n");
        }
        return stringBuilderEquationHistory.toString();
    }
}