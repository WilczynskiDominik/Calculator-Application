package com.example.calculatorfx.model;

import java.util.Stack;

public class EquationHistoryModel {
    //stack where mathModels are saved
    Stack<MathModel> historyOfEquations = new Stack<>();

    //method responsible for adding to the stack equations under the guise new mathModel
    public void add(MathModel mathModel){
        this.historyOfEquations.push(new MathModel(mathModel));
    }
    //method responsible for returning stack
    public Stack<MathModel> getHistory(){
        return historyOfEquations;
    }
}
