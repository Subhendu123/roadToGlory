package com.roadtoglory.itext;

/*
*
*
   This is created by Subhendu (2024) for the project: logging-framework-display
        
   @Package name com.roadtoglory.itext
   @Author Subhendu
   @Date 02-Oct-2024 21:00
*
*
*/
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.kernel.geom.Vector;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;

import java.io.IOException;
import java.util.*;

public class TableExtractionStrategy extends LocationTextExtractionStrategy {

    private List<TextPosition> textPositions = new ArrayList<>();

/*    public void eventOccurred(EventData data, EventType type) {
        if (type == com.itextpdf.kernel.pdf.canvas.parser.EventType.RENDER_TEXT) {
            TextRenderInfo renderInfo = (TextRenderInfo) data;
            String text = renderInfo.getText();
            Vector start = renderInfo.getBaseline().getStartPoint();
            float x = start.get(Vector.I1);
            float y = start.get(Vector.I2);

            // Add text and position to a list
            textPositions.add(new TextPosition(text, x, y));
        }
    }*/

    // Method to process extracted text and group them as a table
    public List<List<String>> extractTable() {
        // Sort by Y position (descending order) to group by rows
        textPositions.sort(Comparator.comparing(TextPosition::getY).reversed());

        // Threshold for detecting a new row
        float rowThreshold = 5f;

        List<List<String>> table = new ArrayList<>();
        List<TextPosition> currentRow = new ArrayList<>();

        for (int i = 0; i < textPositions.size(); i++) {
            TextPosition position = textPositions.get(i);

            // If this is the first item, or if it's close to the previous item (same row)
            if (currentRow.isEmpty() || Math.abs(currentRow.get(currentRow.size() - 1).getY() - position.getY()) < rowThreshold) {
                currentRow.add(position);
            } else {
                // Process the previous row
                table.add(convertRowToList(currentRow));
                currentRow = new ArrayList<>();
                currentRow.add(position);
            }
        }

        // Add the last row
        if (!currentRow.isEmpty()) {
            table.add(convertRowToList(currentRow));
        }

        return table;
    }

    // Convert list of TextPositions into a row of text
    private List<String> convertRowToList(List<TextPosition> row) {
        // Sort by X position to group as columns
        row.sort(Comparator.comparing(TextPosition::getX));

        List<String> rowTexts = new ArrayList<>();
        for (TextPosition position : row) {
            rowTexts.add(position.getText());
        }
        return rowTexts;
    }

    // Helper class to store text and position
    private static class TextPosition {
        private final String text;
        private final float x;
        private final float y;

        public TextPosition(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }

        public String getText() {
            return text;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }
}


