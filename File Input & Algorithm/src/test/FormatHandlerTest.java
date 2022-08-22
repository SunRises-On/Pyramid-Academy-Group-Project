import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormatHandlerTest {

    @Test
    void formateTest() {
        List<String> fileList;
        List<String> formattedList;

        FileInputHandler file = new FileInputHandler();
        fileList = file.GetFile("text.txt");
        formattedList = file.toColumns(fileList, 3);

        // Print each row
        formattedList.forEach(System.out::println);
    }
}