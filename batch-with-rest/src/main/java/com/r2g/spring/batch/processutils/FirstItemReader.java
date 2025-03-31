package com.r2g.spring.batch.processutils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2g.spring.batch.models.LeagueTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


/*
*
*
   This is created by Subhendu (2025) for the project: BatchDemo
        
   @Package name com.r2g.config
   @Author Subhendu
   @Date 23-Jan-2025 07:44
*
*
*/
@Component
public class FirstItemReader implements ItemReader<Integer>
{
    private Logger LOG = LogManager.getLogger(FirstItemReader.class);
    private final String URL = "https://apiv3.apifootball.com/?action=get_standings&league_id=152&APIkey=dbbb5d6de694804684b7195d6f8b935adae2b6e53ab8f3c3ad11fd3b844851ed";


    private List list = Arrays.asList(1,2,3,4,5,6,7,8,9);
    private static List<LeagueTable> leagueTableDtos;


    private int i=0;
    /**
     * Reads a piece of input data and advance to the next one. Implementations
     * <strong>must</strong> return <code>null</code> at the end of the input data set. In
     * a transactional setting, caller might get the same item twice from successive calls
     * (or otherwise), if the first call was in a transaction that rolled back.
     *
     * @return T the item to be processed or {@code null} if the data source is exhausted
     * @throws ParseException                if there is a problem parsing the current record (but the
     *                                       next one may still be valid)
     * @throws NonTransientResourceException if there is a fatal exception in the
     *                                       underlying resource. After throwing this exception implementations should endeavour
     *                                       to return null from subsequent calls to read.
     * @throws UnexpectedInputException      if there is an uncategorised problem with the
     *                                       input data. Assume potentially transient, so subsequent calls to read might
     *                                       succeed.
     * @throws Exception                     if an there is a non-specific error.
     */
    @Override
    public Integer read () throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {

        LOG.info("Inside the read() of the FirstItemReader class....");

        if(i < list.size()){
            Integer data = (Integer) list.get(i++);
            LOG.info("Getting the data in the read() data = "+data+" and the index = "+i);
            return data;
        }
        LOG.info("All the retrieval is done. Nothing is left and index value is "+i);
        i=0;
        return null;
    }


}
