package practice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PDFCreator {

    private static Logger logger = Logger.getLogger(PDFCreator.class);

    static void mergePdfFiles(List<InputStream> inputPdfList,
                              OutputStream outputStream) throws DocumentException, IOException {

        //Create document and pdfReader objects.
        Document document = new Document();
        List<PdfReader> readers =
                new ArrayList<PdfReader>();
        int totalPages = 0;

        //Create pdf Iterator object using inputPdfList.
        Iterator<InputStream> pdfIterator =
                inputPdfList.iterator();

        // Create reader list for the input pdf files.
        while (pdfIterator.hasNext()) {
            InputStream pdf = pdfIterator.next();
            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
        }

        // Create writer for the outputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        //Open document.
        document.open();

        //Contain the pdf data.
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();

        // Iterate and process the reader list.
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = iteratorPDFReader.next();
            //Create page and add content.
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
                document.newPage();
                pdfImportedPage = writer.getImportedPage(
                        pdfReader, currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            currentPdfReaderPage = 1;
        }

        //Close document and outputStream.
        outputStream.flush();
        document.close();
        outputStream.close();

        System.out.println("Pdf files merged successfully.");
    }

    public static void main(String[] args) throws IOException, DocumentException {

//        List<InputStream> inputPdfList = new ArrayList<InputStream>();
//        inputPdfList.add(new FileInputStream("src/main/PDF_Downloads/2GBPDF_File_14.pdf"));
//        inputPdfList.add(new FileInputStream("src/main/PDF_Downloads/2GBPDF_File_42.pdf"));
////        inputPdfList.add(new FileInputStream("src/main/PDF_Downloads/1_5GBPDF1.pdf"));
////        inputPdfList.add(new FileInputStream("src/main/PDF_Downloads/1_5GBPDF2.pdf"));
//        //Prepare output stream for merged pdf file.
//        OutputStream outputStream =
//                new FileOutputStream("src/main/PDF_Downloads/merged.pdf");
//
//        //call method to merge pdf files.
//            mergePdfFiles(inputPdfList, outputStream);


        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/main/PDF_Downloads/2GBPDF_File_"
                + LocalDateTime.now().getHour()+"_"+LocalDateTime.now().getMinute()+"_"+LocalDateTime.now().getSecond()+".pdf"));
        System.out.println("PDF creation is started!");
        //opens the PDF
        doc.open();
        //adds paragraph to the PDF file
//        for(int i=0; i < 90000 * 5; i++){

//            for(int j=0; j<50; j++) {
//        doc.add(new Paragraph("Test text! This is a test pdf created for the 2GB PDF issue! and Line no is " + i + 1));
        for(int i=0;i<1200;i++) {
            Element element = Image.getInstance("src/main/images/image_4.jpg");
            doc.add(element);
        }


        System.out.println("PDF creation is done!");
        //close the PDF file
        doc.close();
        //closes the writer
        writer.close();
    }
}
