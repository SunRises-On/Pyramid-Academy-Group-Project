package com.example.gui.File;
//By DrewTodd0628
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileInputHandlerTest {

    @Test
    void fileContents() {
        FileInputHandler FIH = new FileInputHandler();
        System.out.print(FIH.GetFile("src/main/resources/text.txt"));
    }

}