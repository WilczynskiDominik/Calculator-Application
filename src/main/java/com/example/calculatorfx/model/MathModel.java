package com.example.calculatorfx.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathModel {
    //variable that contains first value of equation
    private BigDecimal firstValue;
    //variable that contains second value of equation
    private BigDecimal secondValue;
    //variable that contains equation character
    private String equationCharacter;
    //variable that is true when in equation is equation character
    private boolean isEquationCharacterIn = false;
    //variable that is ture when in equation is equation character that needs only first value to be solved
    private boolean isOneValueEquationCharacterIn = false;

    public MathModel(){}
    public MathModel(MathModel mathModel){
        this.firstValue = mathModel.firstValue;
        this.secondValue = mathModel.secondValue;
        this.equationCharacter = mathModel.equationCharacter;
        this.isEquationCharacterIn = mathModel.isOneValueEquationCharacterIn;
        this.isOneValueEquationCharacterIn = mathModel.isOneValueEquationCharacterIn;
    }

    //method that is responsible for set first value of equation
    public void setFirstValue(BigDecimal firstValue) {
        this.firstValue = firstValue;
    }
    //method that is responsible for set second value of equation
    public void setSecondValue(BigDecimal secondValue) {
        this.secondValue = secondValue;
    }
    //method that is responsible for set equation character
    public void setEquationCharacter(String equationCharacter) {
        this.equationCharacter = equationCharacter;
        this.isEquationCharacterIn = true;
        if(isOneValueEquationCharacterIn){
            this.isOneValueEquationCharacterIn = false;
        }
        if(hasBeenChosenOneValueEquationCharacter(equationCharacter)){
            this.isOneValueEquationCharacterIn = true;
        }
    }
    //method that returns true when equation character that needs only first value to be solved has been chosen in previous equation
    private boolean hasBeenChosenOneValueEquationCharacter(String equationCharacter){
        switch (equationCharacter){
            case "%", "^2", "sqrt" -> {return true;}
            default -> {return false;}
        }
    }
    //method that returns true when equation character is in equation
    public boolean isEquationCharacterIn(){
        return isEquationCharacterIn;
    }
    //method that returns true when equation character that needs only first value to be solved is in equation
    public boolean isOneValueEquationCharacterIn(){return isOneValueEquationCharacterIn;}
    //method that returns equation character
    public String getEquationCharacter(){
        return equationCharacter;
    }

    @Override
    public String toString() {
        try{
            String equation;
            if(isOneValueEquationCharacterIn){
               equation = addingBracketWhenNegativeValue(1) + " " +
                        equationCharacter + " " + "=" + " " +
                        equation().toString();
            }else{
                 equation = addingBracketWhenNegativeValue(1) + " " +
                        equationCharacter + " " +
                         addingBracketWhenNegativeValue(2) + " " + "=" + " " +
                        equation().toString();
            }
            return equation;
        }catch (Exception e){
            return "ERROR";
        }
    }
    //method responsible for adding bracket when value is negative
    private String addingBracketWhenNegativeValue(int index){
        StringBuilder value = new StringBuilder();
        if(getEquationValue(index).contains("-")){
            value.append("(");
            value.append(getEquationValue(index));
            value.append(")");
            return value.toString();
        }
        return value.append(getEquationValue(index)).toString();
    }
    //method return equation value depending index, 1 - first value , 2 - second value
    private String getEquationValue(int index){
        return switch (index) {
            case 1 -> firstValue.toString();
            case 2 -> secondValue.toString();
            default -> "0";
        };
    }

    //method that returns solved value of equation, throws exception in division equation when second value is zero
    public BigDecimal equation() throws Exception{
        switch (equationCharacter){
            case "+" -> {return firstValue.add(secondValue);}
            case "-" -> {return firstValue.subtract(secondValue);}
            case "x" -> {return firstValue.multiply(secondValue);}
            case "/" -> {
                try{
                    return firstValue.divide(secondValue, 5, RoundingMode.CEILING).stripTrailingZeros();
                }catch (Exception e){
                    throw new Exception("Error");
                }
            }
            case "%" -> {return firstValue.divide(new BigDecimal(100), 2, RoundingMode.CEILING);}
            case "^2" -> {return firstValue.pow(2);}
            case "sqrt" -> {return firstValue.sqrt(new MathContext(3));}
            default -> {return new BigDecimal(0);}
        }
    }
    //method that is responsible for clear all variables
    public void clear(){
        this.firstValue = null;
        this.secondValue = null;
        this.equationCharacter = null;
        this.isEquationCharacterIn = false;
        this.isOneValueEquationCharacterIn = false;
    }
}
