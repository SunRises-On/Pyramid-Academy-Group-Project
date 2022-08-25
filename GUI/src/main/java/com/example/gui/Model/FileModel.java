package com.example.gui.Model;

import com.example.gui.File.FileHandlerMain;

import java.util.ArrayList;

public class FileModel {
    FileHandlerMain handle;
    private int len;
    public FileModel(){
        handle = new FileHandlerMain();
    }
    public ArrayList<String> getListInit(){
        ArrayList<String> list = new ArrayList<>(handle.getDataInit());
        setLen(list.size());
        return list;
    }
    public ArrayList<String> getListFormat(int colNum){
        if(!isValid(colNum) ){
            return new ArrayList<>();
        }
        return new ArrayList<>(handle.getFormattedList(colNum));
    }

    public void setLen( int length) { this.len = length;}

    public boolean isValid( int colNum){
        return colNum <= len && colNum > 0;
    }
}
