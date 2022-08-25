package com.example.gui.Model;

import com.example.gui.File.FileHandlerMain;
import com.example.gui.File.FileInputHandler;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class FileModelTest {

    @Test
    void getListInit() {
        FileHandlerMain fileTest = new FileHandlerMain();
        ArrayList<String> list = new ArrayList<>(fileTest.getDataInit());

        list.forEach(System.out::println);
    }

    /**
     *  Summary: Test if getListFormat() will catch all illegal column number requests.
     * @param repetitionInfo = current test number
     */

    @RepeatedTest ( 30)
    void getListFormat(RepetitionInfo repetitionInfo){
        int colNum = repetitionInfo.getCurrentRepetition();

        FileModel model = new FileModel();

        ArrayList<String> list = model.getListInit();

        if( ! (model.isValid(colNum))){
            System.out.println("Illegal column number = " + colNum);
        }
        else {
            List<String> newList = FileInputHandler.toColumns(list, colNum);
            newList.forEach(System.out::println);
        }
    }
}