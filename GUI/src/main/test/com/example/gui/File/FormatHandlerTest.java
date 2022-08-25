package com.example.gui.File;

import org.junit.jupiter.api.Test;

import java.util.List;

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