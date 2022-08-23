package com.example.gui.Model;

import com.example.gui.File.FileHandlerMain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileModel {
    FileHandlerMain handle;
    public FileModel(){
        handle = new FileHandlerMain();
    }
    public ArrayList<String> getListInit(){
        return handle.getDataInit().stream().collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<String> getListFormat(int colNum){
        return handle.getFormattedList(colNum).stream().collect(Collectors.toCollection(ArrayList::new));
    }
}
