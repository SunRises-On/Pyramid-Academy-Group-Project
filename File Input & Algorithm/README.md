# Pyramid-Academy-Group-Project

Example of usage:
        
        // Create List of file
        List<String> fileList;
        List<String> formattedList;
        
        FileInputHandler file = new FileInputHandler();
        fileList = file.GetFile("text.txt");
        
        // Formate into columns. The 3 in the toColumn method specifies how many columns to create.
        formattedList = file.toColumns(fileList, 3);

        // Print each row
        formattedList.forEach(System.out::println);
