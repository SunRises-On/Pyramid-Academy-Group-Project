package com.example.gui.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//By DrewTodd0628
class FormatHandlerTest {

    @Test
    void formateTest() {
        List<String> fileList;
        List<String> formattedList;

        FileInputHandler file = new FileInputHandler();
        fileList = file.GetFile("src/main/resources/text.txt");
        formattedList = file.toColumns(fileList, 3);

        // Print each row
        formattedList.forEach(System.out::println);
    }
}