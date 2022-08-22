package com.example.gui.Controller;

public class Data {
    private String inputs;
    Data( String inputs){
        this.inputs = inputs;
    }

    public String getInputs() {
        return inputs;
    }
    public void setInputs(String s){
        this.inputs = s;
    }
    @Override
    public String toString(){
        return inputs;
    }
}
