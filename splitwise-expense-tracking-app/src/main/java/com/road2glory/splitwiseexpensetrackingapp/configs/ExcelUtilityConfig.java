package com.road2glory.splitwiseexpensetrackingapp.configs;

import com.road2glory.splitwiseexpensetrackingapp.utility.ExcelUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@Configuration
@Component
public class ExcelUtilityConfig
{

    @Bean
    public ExcelUtility prepareExcelUtilitytolls ()
    {
        System.out.println("I m inside ExcelUtilityConfig");
        return new ExcelUtility();
    }
}
