package com.example.gui.File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInputHandler extends FormatHandler{

    public List<String> GetFile(String url) {

        List<String> fileList = new ArrayList<>();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(url)));

            // Read each line in file
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                fileList.add(line);
            }

            return fileList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}