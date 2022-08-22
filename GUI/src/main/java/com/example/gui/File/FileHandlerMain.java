package com.example.gui.File;

import java.util.List;

public class FileHandlerMain {
    public List<String> getDataInit(){
        List<String> fileList;
        FileInputHandler file = new FileInputHandler();
        fileList = file.GetFile("src/main/resources/text.txt");

        return fileList;
    }
}
