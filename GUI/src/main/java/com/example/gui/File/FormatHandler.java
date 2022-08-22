package com.example.gui.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class FormatHandler {

    private static int longest;

    public static List<String> toColumns(List<String> fileList, int numberOfColumns) {

        // Get longest line's length (used for column spacing)
        longest = longestInt(fileList);
        List<String> formattedList = new ArrayList<>();

        int numOfLines = fileList.size();
        int numOfRows = numOfLines/numberOfColumns;

        // For every row
        for (int l=0; l<numOfLines; l = l+numberOfColumns) {
            formattedList.add(createRow(fileList, l, numberOfColumns));
        }

        return formattedList;
    }

    private static String createRow(List<String> file, int currentLine, int numOfColumns) {

        // Append lines from file into row
        StringBuilder strBuilder = new StringBuilder();

        for (int i=0; i<numOfColumns; i++) {
            // Make sure there is a next line
            String line;
            try {
                line = file.get(currentLine + i);
            }
            catch (Exception e) {
                line = "";
            }
            strBuilder.append(line);

            // Add Column spaces
            if (i<numOfColumns-1) {
                int spacing = longest - line.length();
                for (int n=0; n<spacing; n++) {
                    strBuilder.append(" ");
                }
            }
        }
        String newRow = strBuilder.toString();

        return newRow;
    }

    // Get longest line's length. Helps determine column spacing
    private static int longestInt(List<String> fileList) {
        Optional<String> longestOpt = fileList.stream()
                .sorted((a, b) -> a.length() > b.length() ? -1 : 1)
                .findFirst();

        int length = longestOpt.toString().length();

        return length;
    }
}