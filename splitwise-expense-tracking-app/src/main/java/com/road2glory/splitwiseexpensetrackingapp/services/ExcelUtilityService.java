package com.road2glory.splitwiseexpensetrackingapp.services;

import com.road2glory.splitwiseexpensetrackingapp.models.GmailMessageDetails;
import com.road2glory.splitwiseexpensetrackingapp.models.User;
import com.road2glory.splitwiseexpensetrackingapp.utility.ExcelUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ExcelUtilityService
{


    private static Logger LOG = LogManager.getLogger(ExcelUtilityService.class);
    @Autowired
    private ExcelUtility excelUtility;
    //    private static final SplitwiseLogger LOG = SplitwiseLogger.getLogger();

    public ByteArrayResource exportToExcel (List<GmailMessageDetails> gmailMessageDetailsList, List<User> userDetailsList) throws IOException
    {
        LOG.debug("Entering in the exportToExcel Method.... ");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet gmailSheet = workbook.createSheet("expense_tracker");

        // Header Code
        if (gmailMessageDetailsList != null && gmailMessageDetailsList.size() > 0)
        {
            excelUtility.createAndFetchHeader(gmailSheet);
            excelUtility.convertEMailToExcel(gmailMessageDetailsList, gmailSheet);
        }


        User userToCheck = userDetailsList.size() > 0 ? userDetailsList.stream()
                                                                       .filter(user -> user.getId() == 6646855L)
                                                                       .findFirst()
                                                                       .get() : null;
        if (userToCheck != null)
        // format splitwise data here
        {
            excelUtility.convertSplitwiseToExcel(userToCheck, workbook);
        }
        else
        {
            LOG.info("NO SPLITWISE DATA is going to be written!");
        }

        String excelFileName = "BankStatement_" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
        FileOutputStream fileOut = new FileOutputStream(excelFileName);
        workbook.write(fileOut);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayResource byteArrayResource = null;
        try
        {
            workbook.write(bos);
            byteArrayResource = new ByteArrayResource(bos.toByteArray());
            Files.deleteIfExists(Path.of(excelFileName));
        }
        catch (Exception e)
        {
            LOG.error("The exception occurred during the workbook write " + e);
        }
        finally
        {
            bos.close();
            workbook.close();
            fileOut.close();
        }

        return byteArrayResource;

    }


}
