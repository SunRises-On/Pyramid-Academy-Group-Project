package com.example.gui.File;

import java.util.List;

public class FileHandlerMain {

    List<String> fileList;
    public List<String> getDataInit(){
        FileInputHandler file = new FileInputHandler();
        fileList = file.GetFile("src/main/resources/text.txt");

        return fileList;
    }

    public List<String> getFormattedList( int colNum){

        return FileInputHandler.toColumns(fileList, colNum);
    }

    public void printFormattedList( List<String> formattedList){
        formattedList.forEach(System.out::println);
    }
}
