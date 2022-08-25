package com.example.gui.File;
import org.junit.jupiter.api.Test;

//By DrewTodd0628


class FileInputHandlerTest {

    @Test
    void fileContents() {
        FileInputHandler FIH = new FileInputHandler();
        System.out.print(FIH.GetFile("src/main/resources/text.txt"));
    }

}