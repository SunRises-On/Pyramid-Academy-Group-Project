package com.example.gui.File;

import java.util.List;

public class FileHandlerMain {
    FileInputHandler file;
    List<String> fileList;
    public List<String> getDataInit(){
        file = new FileInputHandler();
        fileList = file.GetFile("src/main/resources/text.txt");

        List<String> formattedList;
        formattedList = file.toColumns(fileList, 3);
        formattedList.forEach(System.out::println);
        return fileList;
    }

    public List<String> getFormattedList( int colNum){
        List<String> formattedList;
        formattedList = file.toColumns(fileList, colNum);
        return formattedList;
    }
}
