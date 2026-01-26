package com.roadtoglory.itext;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.IOException;


/*
*
*
   This is created by Subhendu (2024) for the project: logging-framework-display
        
   @Package name com.roadtoglory.itext
   @Author Subhendu
   @Date 02-Oct-2024 20:19
*
*
*/
public class PDFUtils
{


    public static void main (String[] args) throws IOException
    {
        String path = "C:\\Users\\Subhendu\\Downloads\\D-TBD\\phonepe-statement.pdf";
        String response = readPDF(path);
//        System.out.println("The response is ");
//        System.out.println(response);

    }




    // Method to read text from PDF
    public static String readPDF(String filePath) throws IOException {
        // Initialize PdfReader and PdfDocument
        PdfReader reader = new PdfReader(filePath);
        PdfDocument pdfDoc = new PdfDocument(reader);

        // StringBuilder to store the extracted text
        StringBuilder extractedText = new StringBuilder();
        System.out.println("The no of pages "+pdfDoc.getNumberOfPages());

        // Loop through each page to extract text
        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
            String pageContent = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
            System.out.println("*************************************************************");
            System.out.println(pageContent);
            System.out.println("--------------------------------------------------------------");
            extractedText.append(pageContent);
//            System.out.println("The appended text as of now ");
//            System.out.println("*************************************************************");
//            System.out.println(extractedText);
//            System.out.println("--------------------------------------------------------------");
        }

        // Close the document
        pdfDoc.close();

        return extractedText.toString();
    }


    public static void extractPDFMetadata(String filePath) throws IOException
    {
        PdfReader reader = new PdfReader(filePath);
        PdfDocument pdfDoc = new PdfDocument(reader);

        // Extract PDF metadata
        String title = pdfDoc.getDocumentInfo().toString();
        String author = pdfDoc.getDocumentInfo().getAuthor();

        System.out.println("No of pages "+pdfDoc.getNumberOfPages());

        for(int i=1; i< pdfDoc.getNumberOfPages();i++){
            System.out.println(" page "+pdfDoc.getPage(i).getDocument());
        }

        System.out.println("Title: " + title);
        System.out.println("Author: " + author);

        pdfDoc.close();
    }


}
