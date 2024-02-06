package org.road2glory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class OCRReciptKlass {

    public static void main(String[] args) throws IOException, InvalidFormatException {


        String receiptOcrEndpoint = "https://ocr.asprise.com/api/v1/receipt"; // Receipt OCR API endpoint
        File imageFile = new File("C:\\OneDrive - Cloud Storage\\OneDrive\\Pictures\\Screenshots\\Dmart-sample.pdf");

        System.out.println("=== Java Receipt OCR ===");
        String output = null;
        try {
            HttpPost post = new HttpPost(receiptOcrEndpoint);
            CloseableHttpClient client = HttpClients.createDefault();
            post.setEntity(MultipartEntityBuilder.create()
                    .addTextBody("api_key", "TEST")       // Use 'TEST' for testing purpose
                    .addTextBody("recognizer", "auto")       // can be 'US', 'CA', 'JP', 'SG' or 'auto'
                    .addTextBody("ref_no", "ocr_java_123'") // optional caller provided ref code
                    .addPart("file", new FileBody(imageFile))    // the image file
                    .build());

            try (CloseableHttpResponse response = client.execute(post)) {
                output = EntityUtils.toString(response.getEntity());
                System.out.println("THE OUTPUT IS " +output);
                // Receipt OCR result in JSON
            }
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
// For local json - mock response
       /* File file = new File("src/main/resources/myData.json");
        FileReader reader = new FileReader(file);

        int character;
        StringBuilder outputBuilder = new StringBuilder();
        while ((character = reader.read()) != -1) {
            System.out.print((char) character);
            outputBuilder.append((char) character);
        }
        reader.close();*/
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(output);
        JsonNode node = jsonNode.get("receipts");
        boolean isArr = node.isArray();
        System.out.println("The arr is "+isArr);

        if(isArr){
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("dmart");

            XSSFRow headerRow= sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Description");
            headerRow.createCell(1).setCellValue("Qty");
            headerRow.createCell(2).setCellValue("Unit Price");
            headerRow.createCell(3).setCellValue("Amount");
            int i=0;
            int rowNum = 1;
            Iterator<JsonNode> itemsItr = node.get(0).get("items").iterator();

            while (itemsItr.hasNext()){
                XSSFRow row = sheet.createRow(rowNum);

                JsonNode itemNode = itemsItr.next();

                row.createCell(0).setCellValue(itemNode.get("description").textValue());
                row.createCell(1).setCellValue(itemNode.get("qty") != null
                                                        ? String.valueOf(itemNode.get("qty").intValue()) : "1");
                row.createCell(2).setCellValue(itemNode.get("unitPrice") != null && itemNode.get("unitPrice").textValue() != null
                        ? itemNode.get("unitPrice").textValue() : "NA");
                row.createCell(3).setCellValue(itemNode.get("amount").doubleValue());
                rowNum++;
            }

            OutputStream fileOut = new FileOutputStream("dmart.xlsx");
            workbook.write(fileOut);

            System.out.println("The data is written successfully");
        }



    }
}
