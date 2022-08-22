import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileInputHandlerTest {

    @Test
    void fileContents() {
        FileInputHandler FIH = new FileInputHandler();
        System.out.print(FIH.GetFile("text.txt"));
    }

}